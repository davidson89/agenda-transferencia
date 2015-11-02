package br.com.test.rf.agendaTransf.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.test.rf.agendaTransf.domain.AgendamentoTransf;
import br.com.test.rf.agendaTransf.enumx.TipoAgendamento;

/**
 * @author "davidson.rodrigues"
 *
 * @created 28 de out de 2015
 */
@Repository("agendamentoTransfDAO")
public class AgendamentoTransfDAO extends BaseDAO<AgendamentoTransf> {

	/**
	 * @param dtTransf
	 *            a data na qual a transferência foi agendada.
	 */
	public List<AgendamentoTransf> findByDtTransf(Calendar dtTransf) {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("dataTransferencia", dtTransf));
		return criteria.list();
	}

	/**
	 * @param contaOrig
	 *            contaOrig
	 * @param contaDest
	 *            contaDest
	 * @param tipoAgend
	 *            tipoAgend
	 * @param dataTransf
	 *            dataTransf
	 * @param dataOp
	 *            dataOp
	 * @return uma {@link List} de {@link AgendamentoTransf} de acordo com os
	 *         parâmetros informados
	 */
	public List<AgendamentoTransf> findBy(String contaOrig, String contaDest, TipoAgendamento tipo, Calendar dataOp,
			Calendar dataTransf) {
		Criteria criteria = this.createCriteria();
		Criteria critContOrig = criteria.createCriteria("contaOrigem");
		Criteria critContDest = criteria.createCriteria("contaDestino");

		if (contaOrig != null && !contaOrig.isEmpty()) {
			critContOrig.add(Restrictions.eq("numeroConta", contaOrig));
		}
		if (contaDest != null && !contaDest.isEmpty()) {
			critContDest.add(Restrictions.eq("numeroConta", contaDest));
		}
		if (tipo != null) {
			criteria.add(Restrictions.eq("tipoAgendamento", tipo));
		}
		if (dataOp != null) {
			criteria.add(Restrictions.eq("dataOperacao", dataOp));
		}
		if (dataTransf != null) {
			criteria.add(Restrictions.eq("dataTransferencia", dataTransf));
		}

		return criteria.list();
	}

}
