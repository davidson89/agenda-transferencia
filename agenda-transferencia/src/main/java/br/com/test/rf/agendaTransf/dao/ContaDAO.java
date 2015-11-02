/**
 * 
 */
package br.com.test.rf.agendaTransf.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.test.rf.agendaTransf.domain.Conta;

/**
 * @author "davidson.rodrigues"
 *
 * @created 28 de out de 2015
 */
@Repository
public class ContaDAO extends BaseDAO<Conta> {
	
	/**
	 * 
	 * @param cliente cliente
	 * @param cpfCnpj cpfCnpj
	 * @param numeroConta numeroConta
	 * @return uma lista de {@link Conta}
	 */
	public List<Conta> findBy(String cliente, String cpfCnpj, String numeroConta) {
		Criteria criteria = this.createCriteria();
		Criteria critAgente = criteria.createCriteria("agente");
		
		if(cliente != null && !cliente.isEmpty()) {
			critAgente.add(Restrictions.eq("nome", cliente));
		}
		if(cpfCnpj != null && !cpfCnpj.isEmpty()) {
			critAgente.add(Restrictions.eq("cpfCnpj", cpfCnpj));
		}
		if(numeroConta != null && !numeroConta.isEmpty()) {
			criteria.add(Restrictions.eq("numeroConta", numeroConta));
		}
		
		return criteria.list();
	}
	
	/**
	 * 
	 * @param numeroConta numeroConta
	 * @return a {@link Conta} respectiva ao numero informado
	 */
	public Conta findByNumero(String numeroConta) {
		Criteria criteria = this.createCriteria();
		if(numeroConta != null && !numeroConta.isEmpty()) {
			criteria.add(Restrictions.eq("numeroConta", numeroConta));
		}
		
		return (Conta) criteria.uniqueResult();
	}
}
