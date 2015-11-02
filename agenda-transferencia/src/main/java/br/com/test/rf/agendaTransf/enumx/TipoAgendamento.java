package br.com.test.rf.agendaTransf.enumx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import br.com.test.rf.agendaTransf.util.CalendarUtil;

/**
 * @author "davidson.rodrigues"
 *
 * @created 25 de out de 2015
 */
public enum TipoAgendamento implements AgendamentoStrategy {

	A {

		private final BigDecimal TX_BASE = new BigDecimal("2.00");

		private final BigDecimal FATOR = new BigDecimal(0.03);

		/**
		 * {@inheritDoc}
		 */
		public BigDecimal getTaxaAgendamento(BigDecimal valor, Calendar dtOperacao, Calendar dtAgendamento) {
			return TX_BASE.add(valor.multiply(FATOR)).setScale(2, RoundingMode.DOWN);
		}

	},

	B {
		private final int DIAS_LIMITE = 30;

		private final BigDecimal TX_MAIOR_30_DIAS = new BigDecimal("8.00");

		private final BigDecimal TX_MENOR_30_DIAS = new BigDecimal("10.00");

		/**
		 * {@inheritDoc}
		 */
		public BigDecimal getTaxaAgendamento(BigDecimal valor, Calendar dtOperacao, Calendar dtAgendamento) {
			int days = CalendarUtil.getDaysBetween(dtOperacao, dtAgendamento);

			return days > DIAS_LIMITE ? TX_MAIOR_30_DIAS : TX_MENOR_30_DIAS;
		}

	},

	C {

		private final BigDecimal FT_ATE_5_DIAS = new BigDecimal(0.083);
		
		private final BigDecimal FT_ATE_10_DIAS = new BigDecimal(0.074); 
		
		private final BigDecimal FT_ATE_15_DIAS = new BigDecimal(0.067);
		
		private final BigDecimal FT_ATE_20_DIAS = new BigDecimal(0.054);
		
		private final BigDecimal FT_ATE_25_DIAS = new BigDecimal(0.043);
		
		private final BigDecimal FT_ATE_30_DIAS = new BigDecimal(0.021);
		
		private final BigDecimal FT_MAIOR_30_DIAS = new BigDecimal(0.012);
		
		/**
		 * 
		 * @param days o numero de dias da data de operação até a data de efetivação da transferência.
		 * @return o fator a ser utilizado de acordo com a quantidade de dias
		 */
		public BigDecimal getFatorPorPeriodo(int days) {
			if (days <= 5) {
				return FT_ATE_5_DIAS;
			} else if (days <= 10) {
				return FT_ATE_10_DIAS;
			} else if (days <= 15) {
				return FT_ATE_15_DIAS;
			} else if (days <= 20) {
				return FT_ATE_20_DIAS;
			} else if (days <= 25) {
				return FT_ATE_25_DIAS;
			} else if(days <= 30) {
				return FT_ATE_30_DIAS;
			}
			return FT_MAIOR_30_DIAS;
		}

		/**
		 * {@inheritDoc}
		 */
		public BigDecimal getTaxaAgendamento(BigDecimal valor, Calendar dtOperacao, Calendar dtAgendamento) {
			int days = CalendarUtil.getDaysBetween(dtOperacao, dtAgendamento);
			return this.getFatorPorPeriodo(days).multiply(valor).setScale(2, RoundingMode.DOWN);
		}
	},
	
	D {

		private final BigDecimal FT_ATE_5_DIAS = new BigDecimal(1.083);
		
		private final BigDecimal FT_ATE_10_DIAS = new BigDecimal(1.074); 
		
		private final BigDecimal FT_ATE_15_DIAS = new BigDecimal(1.067);
		
		/**
		 * {@inheritDoc}
		 */
		public BigDecimal getTaxaAgendamento(BigDecimal valor, Calendar dtOperacao, Calendar dtAgendamento) {
			return null;
		}
		
	};

}
