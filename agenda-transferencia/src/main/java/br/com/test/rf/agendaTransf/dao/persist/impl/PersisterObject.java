/**
 * 
 */
package br.com.test.rf.agendaTransf.dao.persist.impl;

import org.hibernate.Session;

import br.com.test.rf.agendaTransf.dao.BaseDAO;
import br.com.test.rf.agendaTransf.dao.persist.api.Persistable;
import br.com.test.rf.agendaTransf.dao.persist.api.Persister;

/**
 * @author "davidson.rodrigues"
 *
 * @created 25 de out de 2015
 */
public class PersisterObject implements Persister {
	
	private Persistable persistable;

	/**
	 * @param persistable um objeto persistivel 
	 */
	public PersisterObject(Persistable persistable) {
		this.persistable = persistable;
	}

	public void save() {
		Session session = null;
		try {
			session = BaseDAO.getSession();
			session.beginTransaction();
			session.saveOrUpdate(this.persistable);
			session.getTransaction().commit();
		} finally {
			closeSession(session);
		}
	}

	public void delete() {
		Session session = null;
		try {
			session = BaseDAO.getSession();
			session.beginTransaction();
			session.delete(this.persistable);
			session.getTransaction().commit();
		} finally {
			closeSession(session);
		}
	}

	/**
	 * @param session
	 *            a session a ser fechada
	 */
	private void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
}
