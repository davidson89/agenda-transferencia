package br.com.test.rf.agendaTransf.domain;

/**
 * @author "davidson.rodrigues"
 *
 * @created 23 de out de 2015
 */
public class Agente {

	private String nome;
	
	private String email;
	
	private String cpfCnpj;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
}
