package br.com.test.rf.agendaTransf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.test.rf.agendaTransf.domain.persist.impl.AbstractPersistableObject;

/**
 * @author "davidson.rodrigues"
 *
 * @created 23 de out de 2015
 */
@Entity
@Table(name="AGENTE")
public class Agente extends AbstractPersistableObject {

	private String cpfCnpj;

	private String nome;
	
	private String email;

	/**
	 * @return the cpfCnpj
	 */
	@Id
	@Column(name="CPF_CNPJ")
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	
	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the email
	 */
	@Column(name="EMAIL", length = 50)
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
	 * @return the nome
	 */
//	@NotEmpty(message="{agente.nome.erro.empty}")
//	@Size(min=2)
	@Column(name="NOME", length = 100)
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
