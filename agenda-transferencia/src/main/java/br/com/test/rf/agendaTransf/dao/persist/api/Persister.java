/**
 * 
 */
package br.com.test.rf.agendaTransf.dao.persist.api;

/**
 * @author "davidson.rodrigues"
 *
 * @created 27 de out de 2015
 */
public interface Persister<Persistable> {
	
	/**
	 * 
	 * @param persistable a entidade a ser persistida
	 */
	public void save(Persistable persistable);
	
	/**
	 * 
	 * @param persistable a entidade a ser removida
	 */
	public void delete(Persistable persistable);

}
