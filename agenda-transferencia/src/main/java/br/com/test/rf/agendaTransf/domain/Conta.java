/**
 * 
 */
package br.com.test.rf.agendaTransf.domain;

import java.math.BigDecimal;

import br.com.test.rf.agendaTransf.exceptions.BusinessException;

/**
 * @author "davidson.rodrigues"
 *
 * @created 23 de out de 2015
 */
public class Conta {
	
	private Agente agente;

	private String numeroConta;

	private BigDecimal saldo = BigDecimal.ZERO;

	private BigDecimal limite = BigDecimal.ZERO;

	/**
	 * @return the numeroConta
	 */
	public String getNumeroConta() {
		return numeroConta;
	}

	/**
	 * @param numeroConta
	 *            the numeroConta to set
	 */
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo
	 *            the saldo to set
	 */
	private void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * Método responsável por garantir que apenas uma thread afete o saldo por vez.
	 * 
	 * @param valor valor
	 * @throws BusinessException caso o novo saldo seja menor que o limite da conta
	 */
	public synchronized void afetarSaldo(BigDecimal valor) throws BusinessException {
		BigDecimal novoSaldo = this.saldo.add(valor);
		if(estourouLimite(novoSaldo)) {
			throw new BusinessException(String.format("O limite foi estourado em R$%.", novoSaldo.add(this.limite)));
		} else {
			this.setSaldo(novoSaldo);
		}
	}

	/**
	 * @param novoSaldo o valor que será o novo saldo da conta, caso não estore o limite
	 * @return <code>true</code> caso o novo saldo seja menor que o limite da conta, <code>false</code> caso contrário.
	 */
	private boolean estourouLimite(BigDecimal novoSaldo) {
		return novoSaldo.add(this.limite).compareTo(BigDecimal.ZERO) < 0;
	}

	/**
	 * @return the limite
	 */
	public BigDecimal getLimite() {
		return limite;
	}

	/**
	 * @param limite
	 *            the limite to set
	 */
	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	/**
	 * @return the agente
	 */
	public Agente getAgente() {
		return agente;
	}

	/**
	 * @param agente the agente to set
	 */
	public void setAgente(Agente agente) {
		this.agente = agente;
	}

}
