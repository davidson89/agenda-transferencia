package br.com.test.rf.agendaTransf.builder;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.test.rf.agendaTransf.domain.AgendamentoTransf;
import br.com.test.rf.agendaTransf.enumx.TipoAgendamento;
import br.com.test.rf.agendaTransf.util.CalendarUtil;

/**
 * Builder para facilitar a criação de um {@link AgendamentoTransf}
 * 
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
public class AgendamentoTransfBuilder {

	private ContaBuilder contaOrigem = ContaBuilder.umaConta();

	private ContaBuilder contaDestino = ContaBuilder.umaConta();

	private BigDecimal valor = new BigDecimal("100.00");

	private TipoAgendamento tipo = TipoAgendamento.A;

	private Calendar dtOperacao = CalendarUtil.getCurrentTruncDate();

	private Calendar dtTransf = CalendarUtil.getCurrentTruncDate();

	public static AgendamentoTransfBuilder umAgendamento() {
		return new AgendamentoTransfBuilder();
	}

	public AgendamentoTransfBuilder deContaOrigem(ContaBuilder contaOrigem) {
		this.contaOrigem = contaOrigem;
		return this;
	}

	public AgendamentoTransfBuilder deContaDesitno(ContaBuilder contaDestino) {
		this.contaDestino = contaDestino;
		return this;
	}

	public AgendamentoTransfBuilder doTipo(TipoAgendamento tipo) {
		this.tipo = tipo;
		return this;
	}

	public AgendamentoTransfBuilder deValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}

	public AgendamentoTransfBuilder comDtOperacao(Calendar dtOp) {
		this.dtOperacao = dtOp;
		return this;
	}

	public AgendamentoTransfBuilder comDtTransf(Calendar dtTransf) {
		this.dtTransf = dtTransf;
		return this;
	}

	/**
	 * 
	 * @return um {@link AgendamentoTransf} de acordo com os parâmetros
	 *         informados
	 */
	public AgendamentoTransf build() {
		AgendamentoTransf agendamento = new AgendamentoTransf();
		agendamento.setContaOrigem(contaOrigem.build());
		agendamento.setContaDestino(contaDestino.build());
		agendamento.setDataOperacao(dtOperacao);
		agendamento.setDataTransferencia(dtTransf);
		agendamento.setTipoAgendamento(tipo);
		agendamento.setValor(valor);
		return agendamento;
	}

}
