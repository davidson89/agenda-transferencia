package br.com.test.rf.agendaTransf.builder;

import br.com.test.rf.agendaTransf.domain.Agente;

/**
 * Builder para facilitar a criação de um {@link Agente}
 * 
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
public class ClienteBuilder {

	private static int i = 1;

	private String nome = "Cliente " + i;

	private String cpfCnpj = "123.456.789-0" + i;

	private String email = "cliente" + i + "@test.com";

	public ClienteBuilder cliente;

	public static ClienteBuilder umCliente() {
		i++;
		return new ClienteBuilder();
	}

	public ClienteBuilder deNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ClienteBuilder deCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
		return this;
	}

	public ClienteBuilder deEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * 
	 * @return um {@link Agente} de acordo com os parâmetros informados
	 */
	public Agente build() {
		Agente agente = new Agente();
		agente.setCpfCnpj(cpfCnpj);
		agente.setNome(nome);
		agente.setEmail(email);
		return agente;
	}

}
