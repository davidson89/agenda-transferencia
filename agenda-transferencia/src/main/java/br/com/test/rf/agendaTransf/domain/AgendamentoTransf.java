package br.com.test.rf.agendaTransf.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.test.rf.agendaTransf.dao.persist.impl.AbstractPersistableObject;
import br.com.test.rf.agendaTransf.enumx.TipoAgendamento;

/**
 * @author "davidson.rodrigues"
 *
 * @created 23 de out de 2015
 */
@Entity
@Table(name = "AGENDAMENTO_TRANSF")
public class AgendamentoTransf extends AbstractPersistableObject {

	private Conta contaOrigem;

	private Conta contaDestino;

	private BigDecimal valor;

	private TipoAgendamento tipo;

	private Calendar dataOperacao;

	private Calendar dataAgendamento;

	/**
	 * @return the contaOrigem
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTA_ORIGEM", nullable = false)
	public Conta getContaOrigem() {
		return contaOrigem;
	}

	/**
	 * @param contaOrigem
	 *            the contaOrigem to set
	 */
	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	/**
	 * @return the contaDestino
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTA_DESTINO", nullable = false)
	public Conta getContaDestino() {
		return contaDestino;
	}

	/**
	 * @param contaDestino
	 *            the contaDestino to set
	 */
	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	/**
	 * @return the valor
	 */
	@Column(name = "VALOR", nullable = false, precision = 20, scale = 2)
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the tipo
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_AGENDAMENTO")
	public TipoAgendamento getTipoAgendamento() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipoAgendamento(TipoAgendamento tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the dataOperacao
	 */
	@Column(name = "DT_OPERACAO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataOperacao() {
		return dataOperacao;
	}

	/**
	 * @param dataOperacao
	 *            the dataOperacao to set
	 */
	public void setDataOperacao(Calendar dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	/**
	 * @return the dataAgendamento
	 */
	@Column(name = "DT_AGEDAMENTO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataAgendamento() {
		return dataAgendamento;
	}

	/**
	 * @param dataAgendamento
	 *            the dataAgendamento to set
	 */
	public void setDataAgendamento(Calendar dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	/**
	 * 
	 * @return o valor da taxa de transferência
	 */
	@Transient
	public BigDecimal getTaxaTransferencia() {
		return this.getTipoAgendamento().getTaxaAgendamento(valor, this.getDataOperacao(), this.getDataAgendamento());
	}
}
