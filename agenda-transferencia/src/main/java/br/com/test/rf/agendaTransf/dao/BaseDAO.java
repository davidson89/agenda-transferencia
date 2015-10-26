package br.com.test.rf.agendaTransf.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.test.rf.agendaTransf.dao.persist.api.Persistable;
import br.com.test.rf.agendaTransf.domain.Agente;

/**
 * 
 * @author "davidson.rodrigues"
 *
 * @created 25 de out de 2015
 */
public class BaseDAO<T extends Persistable> implements GenericBaseDAO<T> {

	private static final SessionFactory sessionFactory;

	private Class<T> clazz;

	public BaseDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public T findByPk(Serializable pk) {
		try {
			Criteria criteria = getSession().createCriteria(this.clazz);
			return (T) criteria.add(Restrictions.idEq(pk)).uniqueResult();
		} finally {
			this.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			Criteria criteria = getSession().createCriteria(this.clazz);
			return criteria.list();
		} finally {
			this.close();
		}
	}

	/**
	 * Utiliza o arquivo hibernate.cfg.xml, buscando as informações de configuração. É aqui que os models são
	 * capturados, caso a entidade mapeada não exista ela é criada. Caso seja modificada é atualizada. Não deleta as
	 * tabelas.
	 */
	static {
		try {
			AnnotationConfiguration cfg = new AnnotationConfiguration().configure();
			sessionFactory = cfg.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * @return Retorna a sessão.
	 * @throws HibernateException Excessões relacionados ao banco de dados.
	 */
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	/**
	 * Fecha uma sessão.
	 */
	public void close() {
		if (getSession() != null && getSession().isOpen()) {
			getSession().close();
		}
	}
}
