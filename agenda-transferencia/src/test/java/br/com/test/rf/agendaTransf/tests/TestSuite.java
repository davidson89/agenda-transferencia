package br.com.test.rf.agendaTransf.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.test.rf.agendaTransf.tests.actor.EfetivaAgendamentosActorTest;
import br.com.test.rf.agendaTransf.tests.tipoAgendamento.TipoAgendamentoTest;

/**
 * @author "davidson.rodrigues"
 *
 * @created 2 de nov de 2015
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ EfetivaAgendamentosActorTest.class, TipoAgendamentoTest.class })
public class TestSuite {

}
