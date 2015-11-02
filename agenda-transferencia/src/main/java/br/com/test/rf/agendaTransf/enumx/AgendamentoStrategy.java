package br.com.test.rf.agendaTransf.enumx;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Classe que define um contrato para o calculo de taxa de agendamento.
 * 
 * @author "davidson.rodrigues"
 *
 * @created 25 de out de 2015
 */
public interface AgendamentoStrategy {

	/**
	 * 
	 * @param valor o valor a ser transferido
	 * @param dtOperacao a data de realização do agendamento
	 * @param dtAgendamento a data para o dinheiro ser transferido
	 * @return a taxa aplicada sobre o valor
	 */
	public BigDecimal getTaxaAgendamento(BigDecimal valor, Calendar dtOperacao, Calendar dtAgendamento);

}
