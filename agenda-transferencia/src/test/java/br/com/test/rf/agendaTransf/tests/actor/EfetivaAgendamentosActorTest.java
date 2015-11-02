package br.com.test.rf.agendaTransf.tests.actor;

import static br.com.test.rf.agendaTransf.builder.AgendamentoTransfBuilder.umAgendamento;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.test.rf.agendaTransf.actor.EfetivaAgendamentosActor;
import br.com.test.rf.agendaTransf.dao.AgendamentoTransfDAO;
import br.com.test.rf.agendaTransf.dao.ContaDAO;
import br.com.test.rf.agendaTransf.domain.AgendamentoTransf;

/**
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class EfetivaAgendamentosActorTest {

	@Mock
	private AgendamentoTransfDAO agendDAO;

	@Mock
	private ContaDAO contaDAO;

	@InjectMocks
	private EfetivaAgendamentosActor actor;

	@Test
	public void testEfefivaAgendamentos() {
		AgendamentoTransf agendamento = umAgendamento().build();
		Mockito.when(agendDAO.findByDtTransf(agendamento.getDataTransferencia()))
				.thenReturn(Arrays.asList(agendamento));

		BigDecimal valor = agendamento.getValor();
		BigDecimal taxa = agendamento.getTaxaTransferencia();
		BigDecimal vlTotalDebitado = valor.add(taxa);

		BigDecimal saldoInicialContaOrig = agendamento.getContaOrigem().getSaldo();
		BigDecimal saldoInicialContaDestino = agendamento.getContaDestino().getSaldo();

		actor.efetivaAgendamentos();

		BigDecimal saldoFinalContaOrig = agendamento.getContaOrigem().getSaldo();
		BigDecimal saldoFinalContaDestino = agendamento.getContaDestino().getSaldo();

		assertEquals("Saldo da conta origem não está correto: ", saldoInicialContaOrig.subtract(vlTotalDebitado),
				saldoFinalContaOrig);
		assertEquals("Saldo da conta destino não está correto: ", saldoInicialContaDestino.add(valor),
				saldoFinalContaDestino);
	}

	@Test
	public void testEfetivacaoComErro() {
		// Alterando o valor do agendamento para extourar o limite da conta
		// origem
		AgendamentoTransf agendamento = umAgendamento().build();
		BigDecimal valorTotalDisponivel = agendamento.getContaOrigem().getValorTotalDisponivel();
		agendamento.setValor(valorTotalDisponivel.add(BigDecimal.ONE));

		Mockito.when(agendDAO.findByDtTransf(agendamento.getDataTransferencia()))
				.thenReturn(Arrays.asList(agendamento));

		BigDecimal saldoInicialContaOrig = agendamento.getContaOrigem().getSaldo();
		BigDecimal saldoInicialContaDestino = agendamento.getContaDestino().getSaldo();

		actor.efetivaAgendamentos();

		BigDecimal saldoFinalContaOrig = agendamento.getContaOrigem().getSaldo();
		BigDecimal saldoFinalContaDestino = agendamento.getContaDestino().getSaldo();

		// Os saldos não devem ser afetados, pois o valor debitado é maior que o
		// total disponível na conta.
		assertEquals("Saldo da conta origem não está correto: ", saldoInicialContaOrig, saldoFinalContaOrig);
		assertEquals("Saldo da conta destino não está correto: ", saldoInicialContaDestino, saldoFinalContaDestino);

	}

}
