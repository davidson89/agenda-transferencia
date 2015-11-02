package br.com.test.rf.agendaTransf.dao;

import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.GenericTypeResolver;

import br.com.test.rf.agendaTransf.dao.persist.api.Persistable;
import br.com.test.rf.agendaTransf.dao.persist.api.Persister;

/**
 * @author "davidson.rodrigues"
 * @created 25 de out de 2015
 */
public abstract class BaseDAO<T extends Persistable> implements GenericBaseDAO<T>, Persister<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<? extends T> persistableType;

	/**
	 * @return the {@link EntityManager}
	 */
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@SuppressWarnings("unchecked")
	public T findByPk(Serializable pk) {
		Criteria criteria = createCriteria();
		return (T) criteria.add(Restrictions.idEq(pk)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria criteria = createCriteria();
		return criteria.list();
	}
	
	/**
	 * 
	 * @return uma {@link Criteria} da persistable class da classe
	 */
	public Criteria createCriteria() {
		Session session = this.getEntityManager().unwrap(Session.class);
		return session.createCriteria(this.getPersistableClass());
	}

	/**
	 * Devolve a classe gerenciada pelo DAO.
	 * 
	 * @return a classe gerenciada pelo DAO.
	 */
	@SuppressWarnings("unchecked")
	protected Class<? extends T> getPersistableClass() {
		if (persistableType != null) {
			return persistableType;
		}
		try {
			persistableType =  (Class<? extends T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDAO.class);
			return persistableType;
		} catch (IllegalArgumentException e) {
			throw new PersistenceException("Não foi possível resolver o tipo genérico da classe: " + this.getClass().getName());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void save(T persistable){
		this.getEntityManager().persist(persistable);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(T persistable) {
		this.getEntityManager().remove(persistable);
	}
}
