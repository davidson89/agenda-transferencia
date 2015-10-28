package br.com.test.rf.agendaTransf.dao.persist.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PersistenceException;
import javax.persistence.Transient;

import br.com.test.rf.agendaTransf.dao.persist.api.Persistable;

/**
 * @author "davidson.rodrigues"
 *
 * @created 25 de out de 2015
 */
@MappedSuperclass
public abstract class AbstractPersistableObject implements Persistable, Serializable {

	private static Map<Class<? extends AbstractPersistableObject>, Method> getterCache = new ConcurrentHashMap<Class<? extends AbstractPersistableObject>, Method>();
	
    private static Map<Class<? extends AbstractPersistableObject>, Field> fieldCache =
            new ConcurrentHashMap<Class<? extends AbstractPersistableObject>, Field>();

	/**
	 * Devolve a chave primária.
	 * 
	 * @return a chave primária.
	 */
	@Transient
	public Serializable getPk() {
		try {
			Method method = this.findPkGetter();
			if (method != null) {
				return (Serializable) method.invoke(this, (Object[]) null);
			}
			Field field = this.findPkField();
			if (field != null) {
				if (field.isAccessible()) {
					return (Serializable) field.get(this);
				} else {
					field.setAccessible(true);
					Serializable pk = (Serializable) field.get(this);
					field.setAccessible(false);
					return pk;
				}
			}
		} catch (ReflectiveOperationException e) {
			throw new PersistenceException("Não foi possível capturar a chave primária.", e); 
		} catch (ClassCastException e) {
			throw new PersistenceException("O pk não é do tipo Serializable.", e); 
		}
		throw new PersistenceException("Não foi possível encontra a chave primária da entidade.");
	}

	/**
	 * Devolve o método que retorna a chave primária.
	 * 
	 * @return o método que retorna a chave primária.
	 */
	private Method findPkGetter() {
		if (getterCache.containsKey(this.getClass())) {
			return getterCache.get(this.getClass());
		}

		Method getter = null;
		Method[] methods = this.getClass().getMethods();
		for (Method method : methods) {
			Id id = method.getAnnotation(Id.class);
			if (id != null) {
				getter = method;
				break;
			}
		}
		if (getter != null) {
			getterCache.put(this.getClass(), getter);
		}
		return getter;
	}

	/**
	 * Devolve o campo que contém a chave primária.
	 * 
	 * @return o campo que contém a chave primária.
	 */
	private Field findPkField() {
		if (fieldCache.containsKey(this.getClass())) {
			return fieldCache.get(this.getClass());
		}

		Field pkField = null;
		for (Class<?> clazz = this.getClass(); !clazz.equals(Object.class)
				&& pkField == null; clazz = clazz.getSuperclass()) {
			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				Id id = field.getAnnotation(Id.class);
				if (id != null) {
					pkField = field;
					break;
				}
			}
		}
		if (pkField != null) {
			fieldCache.put(this.getClass(), pkField);
		}
		return pkField;
	}
}
