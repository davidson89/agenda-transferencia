package br.com.test.rf.agendaTransf.listener;

import java.util.Timer;
import java.util.TimerTask;

import br.com.test.rf.agendaTransf.actor.EfetivaAgendamentosActor;
import br.com.test.rf.agendaTransf.util.CalendarUtil;

/**
 * Listener responsável por verificar a hora para execução do processo de
 * efetivação de agendamentos. No caso o tempo de verificação é de 24horas.
 * 
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
public class EfetivaAgendamentoListener extends TimerTask {

	private final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;

	private EfetivaAgendamentosActor actor;

	/**
	 * Constructor
	 */
	public EfetivaAgendamentoListener(EfetivaAgendamentosActor actor) {
		this.actor = actor;
		new Timer().schedule(this, CalendarUtil.getCurrentTruncDate().getTime(), ONCE_PER_DAY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		actor.efetivaAgendamentos();
	}

}
