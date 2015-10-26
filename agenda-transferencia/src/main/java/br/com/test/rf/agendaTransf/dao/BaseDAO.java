package br.com.test.rf.agendaTransf.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.test.rf.agendaTransf.dao.persist.api.Persistable;

/**
 * @author "davidson.rodrigues"
 * @created 25 de out de 2015
 */
public class BaseDAO<T extends Persistable> implements GenericBaseDAO<T> {

    @PersistenceContext
    private EntityManager entityManager;

    // private static final SessionFactory sessionFactory;

    private Class<T> clazz;

    public BaseDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @return the {@link EntityManager}
     */
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @SuppressWarnings("unchecked")
    public T findByPk(Serializable pk) {
        // try {
        Session session = this.getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(this.clazz);
        return (T) criteria.add(Restrictions.idEq(pk)).uniqueResult();
        // } finally {
        // this.close();
        // }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        //
        // EntityManager entityManager = this.getEntityManager();
        // CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();
        // CriteriaQuery<T> criteriaQuery = critBuilder.createQuery(clazz);
        // Root<T> variableRoot = criteriaQuery.from(clazz);
        // criteriaQuery.select(variableRoot);
        // return entityManager.createQuery(criteriaQuery).getResultList();
        // try {
        Session session = this.getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(this.clazz);
        return criteria.list();
        // } finally {
        // this.close();
        // }
    }
}
