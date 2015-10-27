package br.com.test.rf.agendaTransf.dao.persist.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.test.rf.agendaTransf.dao.persist.api.Persistable;
import br.com.test.rf.agendaTransf.dao.persist.api.Persister;

/**
 * @author "davidson.rodrigues"
 * @created 25 de out de 2015
 */
@Repository
public class PersisterObject implements Persister {
	
	public PersisterObject() {}

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @return the {@link EntityManager}
     */
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    private Persistable persistable;

    /**
     * @param persistable um objeto persistivel
     */
    public PersisterObject(Persistable persistable) {
        this.persistable = persistable;
    }

    /**
     * Persiste a entidade
     */
    public void save() {
        getEntityManager().persist(persistable);
    }

    /**
     * Remove a entidade do banco
     */
    public void delete() {
        getEntityManager().remove(persistable);
    }

}
