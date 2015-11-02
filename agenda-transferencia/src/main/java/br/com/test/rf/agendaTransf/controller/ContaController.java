package br.com.test.rf.agendaTransf.controller;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.test.rf.agendaTransf.dao.ContaDAO;
import br.com.test.rf.agendaTransf.domain.Agente;
import br.com.test.rf.agendaTransf.domain.Conta;
import br.com.test.rf.agendaTransf.exceptions.BusinessException;

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
	public ModelAndView list(Model model) {
		model.addAttribute("contas", contaDAO.findAll());
		return new ModelAndView("contas", model.asMap());
	}
	
	@RequestMapping("buscaContaOuVaiParaForm")
	@Transactional
	public String redirectToSearchOrNewConta(HttpServletRequest request) {
		String submit = request.getParameter("submit");
		if (submit != null) {
			if(submit.equals("pesquisa")) {
				return "forward:/buscaConta";
			} else if(submit.equals("novo")) {
				return "redirect:novaConta";
			}				
		}
		return "redirect:contas";
	}
	

	@RequestMapping(value = "buscaConta")
	@Transactional
	public ModelAndView search(Model model, HttpServletRequest request) {
		String cliente = request.getParameter("nomeCliente");
		String cpfCnpj = request.getParameter("cpfCnpj");
		String numeroConta = request.getParameter("numeroConta");

		model.addAttribute("contas", contaDAO.findBy(cliente, cpfCnpj, numeroConta));
		return new ModelAndView("contas", model.asMap());
	}
	
	@RequestMapping(value = "novaConta")
	public ModelAndView newConta() {
		return new ModelAndView("contaFormulario");
	}

	@RequestMapping("removeConta")
	@Transactional
	public ModelAndView remove(Model model, Conta conta) {
		contaDAO.delete(contaDAO.findByPk(conta.getPk()));
		model.addAttribute("contas", contaDAO.findAll());
		return new ModelAndView("contas", model.asMap());
	}

	@RequestMapping(value= "inserirConta", method = RequestMethod.POST)
	@Transactional
	public String inserirConta(HttpServletRequest request) {
		try {
			Conta conta = new Conta();
			conta.setNumeroConta(request.getParameter("numeroConta"));
			conta.setLimite(new BigDecimal(request.getParameter("limite")));
			conta.afetarSaldo(new BigDecimal(request.getParameter("saldoInicial")));
			
			Agente agente = new Agente();
			agente.setNome(request.getParameter("nomeCliente"));
			agente.setEmail(request.getParameter("email"));
			agente.setCpfCnpj(request.getParameter("cpfCnpj"));
			conta.setAgente(agente);
			
			contaDAO.save(conta);
		} catch (Exception e) {
			
		}
		return "redirect:contas";
	}

}
