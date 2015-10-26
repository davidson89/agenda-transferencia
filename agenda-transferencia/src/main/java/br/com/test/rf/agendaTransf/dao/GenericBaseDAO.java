package br.com.test.rf.agendaTransf.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericBaseDAO<Persistable> {
	
	public Persistable findByPk(Serializable pk);
	
	public List<Persistable> findAll();

}
