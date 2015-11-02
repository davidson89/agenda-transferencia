package br.com.test.rf.agendaTransf.actor;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.test.rf.agendaTransf.dao.AgendamentoTransfDAO;
import br.com.test.rf.agendaTransf.dao.ContaDAO;
import br.com.test.rf.agendaTransf.domain.AgendamentoTransf;
import br.com.test.rf.agendaTransf.domain.Conta;
import br.com.test.rf.agendaTransf.exceptions.BusinessException;
import br.com.test.rf.agendaTransf.util.CalendarUtil;

/**
 * Actor responsável por efetivar todos os agendamentos do dia
 * 
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
@Repository("efetivAgendActor")
public class EfetivaAgendamentosActor {

	private static final Logger LOG = Logger.getLogger(EfetivaAgendamentosActor.class);

	@Autowired
	private AgendamentoTransfDAO agendDAO;

	@Autowired
	private ContaDAO contaDAO;

	/**
	 * Efetiva todos agendamento para o dia atual
	 */
	@Transactional
	public void efetivaAgendamentos() {
		List<AgendamentoTransf> agendamentos = agendDAO.findByDtTransf(CalendarUtil.getCurrentTruncDate());
		for (AgendamentoTransf agend : agendamentos) {
			BigDecimal valorAAfetarContaOrig = agend.getValor().add(agend.getTaxaTransferencia()).negate();
			BigDecimal valorAAfetarContaDest = agend.getValor();
			Conta contaOrigem = agend.getContaOrigem();
			Conta contaDestino = agend.getContaDestino();
			try {
				contaOrigem.afetarSaldo(valorAAfetarContaOrig);
				contaDestino.afetarSaldo(valorAAfetarContaDest);

				// TODO o correto nesse ponto seria iniciar uma transação e
				// commitar apenas quando as duas contas forem salvas, caso
				// contrário deveria dar rollback em tudo
				contaDAO.save(contaOrigem);
				contaDAO.save(contaDestino);
			} catch (BusinessException e) {
				// Neste ponto o correto seria criar uma entidade de
				// AgendamentosPendentes para efetivação manual posterior
				LOG.error(e.getMessage());
				LOG.error(String.format("Não foi possível debitar o valor %f da conta %s.",
						valorAAfetarContaOrig.doubleValue(), contaOrigem.getNumeroConta()));
			}
		}
	}

}
