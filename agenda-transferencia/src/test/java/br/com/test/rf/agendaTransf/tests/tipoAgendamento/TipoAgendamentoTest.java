/**
 * 
 */
package br.com.test.rf.agendaTransf.tests.tipoAgendamento;

import static br.com.test.rf.agendaTransf.enumx.TipoAgendamento.A;
import static br.com.test.rf.agendaTransf.enumx.TipoAgendamento.B;
import static br.com.test.rf.agendaTransf.enumx.TipoAgendamento.C;
import static br.com.test.rf.agendaTransf.enumx.TipoAgendamento.D;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.test.rf.agendaTransf.util.CalendarUtil;

/**
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class TipoAgendamentoTest {

	private static final String MSG_TIPO_D = "O valor da taxa não corresponde ao esperado pelo tipo D: ";

	private static final String MSG_TIPO_A = "O valor da taxa não corresponde ao esperado pelo tipo A: ";

	private static final String MSG_TIPO_B_ATE_30 = "O valor da taxa não corresponde ao esperado pelo tipo B até 30 dias: ";

	private static final String MSG_TIPO_B_MAIOR_30 = "O valor da taxa não corresponde ao esperado pelo tipo B maior que 30 dias: ";

	private static final String MSG_TIPO_C_ATE_D = "O valor da taxa não corresponde ao esperado pelo tipo C até %d dias: ";

	private static final String MSG_TIPO_C_MAIOR_30 = "O valor da taxa não corresponde ao esperado pelo tipo C maior que 30 dias: ";

	private static final BigDecimal RESULT_TIPO_A = new BigDecimal("602.00");

	private static final BigDecimal RESULT_TIPO_B_ATE_30 = new BigDecimal("10.00");

	private static final BigDecimal RESULT_TIPO_B_MAIOR_30 = new BigDecimal("8.00");

	private static final BigDecimal RESULT_TIPO_C_ATE_5 = new BigDecimal("16600.00");

	private static final BigDecimal VL_TIPO_A = new BigDecimal("20000.00");

	private static final BigDecimal VL_TIPO_B = new BigDecimal("50000.00");

	private static final BigDecimal VL_TIPO_C = new BigDecimal("200000.00");

	private static final Calendar DT_OPERACAO = CalendarUtil.getCurrentTruncDate();

	@Test
	public void testTaxaTipoA() {
		Calendar dtOpMais1 = CalendarUtil.addDays(DT_OPERACAO, 1);
		BigDecimal taxa = A.getTaxaAgendamento(VL_TIPO_A, DT_OPERACAO, dtOpMais1);
		assertEquals(MSG_TIPO_A, RESULT_TIPO_A, taxa);
	}

	@Test
	public void testTaxaTipoB() {
		// Test até 30 dias
		Calendar dtAte30D = CalendarUtil.addDays(DT_OPERACAO, 30);
		BigDecimal taxa = B.getTaxaAgendamento(VL_TIPO_B, DT_OPERACAO, dtAte30D);
		assertEquals(MSG_TIPO_B_ATE_30, RESULT_TIPO_B_ATE_30, taxa);

		// Test maior que 30 dias
		Calendar dtMaior30D = CalendarUtil.addDays(DT_OPERACAO, 40);
		taxa = B.getTaxaAgendamento(VL_TIPO_B, DT_OPERACAO, dtMaior30D);
		assertEquals(MSG_TIPO_B_MAIOR_30, RESULT_TIPO_B_MAIOR_30, taxa);
	}

	@Test
	public void testTaxaTipoC() {
		// Test até 5 dias
		Calendar dtAte5D = CalendarUtil.addDays(DT_OPERACAO, 5);
		BigDecimal taxa = C.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dtAte5D);
		assertEquals(format(MSG_TIPO_C_ATE_D, 5), RESULT_TIPO_C_ATE_5, taxa);

		// Test até 10 dias
		Calendar dtAte10D = CalendarUtil.addDays(DT_OPERACAO, 10);
		taxa = C.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dtAte10D);
		assertEquals(format(MSG_TIPO_C_ATE_D, 10), new BigDecimal("14800.00"), taxa);

		// Test até 15 dias
		Calendar dtAte15D = CalendarUtil.addDays(DT_OPERACAO, 15);
		taxa = C.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dtAte15D);
		assertEquals(format(MSG_TIPO_C_ATE_D, 15), new BigDecimal("13400.00"), taxa);

		// Test até 20 dias
		Calendar dtAte20D = CalendarUtil.addDays(DT_OPERACAO, 20);
		taxa = C.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dtAte20D);
		assertEquals(format(MSG_TIPO_C_ATE_D, 20), new BigDecimal("10800.00"), taxa);

		// Test até 25 dias
		Calendar dtAte25D = CalendarUtil.addDays(DT_OPERACAO, 25);
		taxa = C.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dtAte25D);
		assertEquals(format(MSG_TIPO_C_ATE_D, 25), new BigDecimal("8600.00"), taxa);

		// Test até 30 dias
		Calendar dtAte30D = CalendarUtil.addDays(DT_OPERACAO, 30);
		taxa = C.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dtAte30D);
		assertEquals(format(MSG_TIPO_C_ATE_D, 30), new BigDecimal("4200.00"), taxa);

		// Test maior que 30 dias
		Calendar dtMaior30D = CalendarUtil.addDays(DT_OPERACAO, 40);
		taxa = C.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dtMaior30D);
		assertEquals(MSG_TIPO_C_MAIOR_30, new BigDecimal("2400.00"), taxa);
	}

	@Test
	public void testTaxaTipoD() {
		// Resultado esperado do tipo A
		Calendar dt = CalendarUtil.addDays(DT_OPERACAO, 1);
		BigDecimal taxa = D.getTaxaAgendamento(VL_TIPO_A, DT_OPERACAO, dt);
		assertEquals(MSG_TIPO_D, RESULT_TIPO_A, taxa);

		// Resultado esperado do tipo B
		taxa = D.getTaxaAgendamento(VL_TIPO_B, DT_OPERACAO, dt);
		assertEquals(MSG_TIPO_D, RESULT_TIPO_B_ATE_30, taxa);

		// Resultado esperado do tipo C
		taxa = D.getTaxaAgendamento(VL_TIPO_C, DT_OPERACAO, dt);
		assertEquals(MSG_TIPO_D, RESULT_TIPO_C_ATE_5, taxa);
	}

}
