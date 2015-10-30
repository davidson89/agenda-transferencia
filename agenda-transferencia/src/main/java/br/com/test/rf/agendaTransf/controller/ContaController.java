/**
 * 
 */
package br.com.test.rf.agendaTransf.controller;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.test.rf.agendaTransf.dao.ContaDAO;
import br.com.test.rf.agendaTransf.domain.Agente;
import br.com.test.rf.agendaTransf.domain.Conta;

/**
 * @author "davidson.rodrigues"
 *
 * @created 26 de out de 2015
 */
@Controller
public class ContaController {

	@Autowired
	private ContaDAO contaDAO;

	@RequestMapping("contas")
	@Transactional
	public String form(Model model) {
		Agente agente = new Agente();
		agente.setCpfCnpj("23123214214");
		agente.setNome("Davidson");
		agente.setEmail("test@test.com");
		
		Conta conta = new Conta();
		conta.setAgente(agente);
		conta.setNumeroConta("12345-5");
		conta.setLimite(new BigDecimal("500.00"));
		
		contaDAO.save(conta);
		model.addAttribute("contas", contaDAO.findAll());
		return "conta/list";
	}

}
