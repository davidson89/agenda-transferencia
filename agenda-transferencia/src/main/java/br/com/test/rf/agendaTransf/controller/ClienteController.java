/**
 * 
 */
package br.com.test.rf.agendaTransf.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.test.rf.agendaTransf.domain.Agente;

/**
 * @author "davidson.rodrigues"
 *
 * @created 26 de out de 2015
 */
@Controller
public class ClienteController {

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("novoCliente")
	public String form(Model model) {

		return "";
	}

}
