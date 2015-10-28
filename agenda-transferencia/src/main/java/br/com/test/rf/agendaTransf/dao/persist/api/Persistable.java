package br.com.test.rf.agendaTransf.dao.persist.api;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * @author "davidson.rodrigues"
 *
 * @created 25 de out de 2015
 */
public interface Persistable {
	
	public Serializable getPk();
	
}
