package br.com.test.rf.agendaTransf.builder;

import java.math.BigDecimal;

import br.com.test.rf.agendaTransf.domain.Conta;
import br.com.test.rf.agendaTransf.exceptions.BusinessException;

/**
 * Builder para facilitar a criação de um {@link Conta}
 * 
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
public class ContaBuilder {

	private static int i = 10000;

	private String numeroConta = i + "-0";

	private BigDecimal saldo = new BigDecimal("1000.00");

	private BigDecimal limite = new BigDecimal("500.00");

	public ClienteBuilder cliente = ClienteBuilder.umCliente();

	public static ContaBuilder umaConta() {
		i++;
		return new ContaBuilder();
	}

	public ContaBuilder deNumero(String numeroConta) {
		this.numeroConta = numeroConta;
		return this;
	}

	public ContaBuilder comSaldo(BigDecimal saldo) {
		this.saldo = saldo;
		return this;
	}

	public ContaBuilder deLimite(BigDecimal limite) {
		this.limite = limite;
		return this;
	}

	public ContaBuilder doCliente(ClienteBuilder cliente) {
		this.cliente = cliente;
		return this;
	}

	/**
	 * 
	 * @return uma {@link Conta} de acordo com os parâmetros informados
	 */
	public Conta build() {
		try {
			Conta conta = new Conta();
			conta.setNumeroConta(numeroConta);
			conta.afetarSaldo(saldo);
			conta.setLimite(limite);
			conta.setAgente(cliente.build());
			return conta;
		} catch (BusinessException e) {
			return null;
		}
	}

}
