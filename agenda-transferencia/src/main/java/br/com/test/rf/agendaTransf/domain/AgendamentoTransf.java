package br.com.test.rf.agendaTransf.domain;

import java.math.BigDecimal;

import br.com.test.rf.agendaTransf.enumx.TipoAgendamento;

/**
 * @author "davidson.rodrigues"
 *
 * @created 23 de out de 2015
 */
public class AgendamentoTransf {

	private Conta contaOrigem;
	
	private Conta contaDestino;
	
	private BigDecimal valor;
	
	private TipoAgendamento tipo;
	
}
