package br.com.test.rf.agendaTransf.controller;

import java.math.BigDecimal;

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

/**
 * @author "davidson.rodrigues"
 *
 * @created 26 de out de 2015
 */
@Controller
public class ContaController {

	@Autowired
	private ContaDAO contaDAO;

	/**
	 * Trata uma requisição da tela de listagem de contas
	 * 
	 * @param model
	 *            model
	 * @return uma requisição para ir para a tela de listagem de contas
	 */
	@RequestMapping("contas")
	@Transactional
	public ModelAndView list(Model model) {
		model.addAttribute("contas", contaDAO.findAll());
		return new ModelAndView("contas", model.asMap());
	}

	/**
	 * Verifica qual foi a requisição realizada (pesquisa ou novo) e
	 * redirecional para a {@link RequestMapping} correta.
	 * 
	 * @param request
	 *            request
	 * @return uma nova requisição
	 */
	@RequestMapping("buscaContaOuVaiParaForm")
	@Transactional
	public String redirectToSearchOrNewConta(HttpServletRequest request) {
		String submit = request.getParameter("submit");
		if (submit != null) {
			if (submit.equals("pesquisa")) {
				return "forward:/buscaConta";
			} else if (submit.equals("novo")) {
				return "redirect:novaConta";
			}
		}
		return "redirect:contas";
	}

	/**
	 * Filtra as contas de acordo com os parâmetros da tela
	 * 
	 * @param model
	 *            model
	 * @param request
	 *            request
	 * @return uma {@link ModelAndView} com as contas filtrados
	 */
	@RequestMapping(value = "buscaConta")
	@Transactional
	public ModelAndView search(Model model, HttpServletRequest request) {
		String cliente = request.getParameter("nomeCliente");
		String cpfCnpj = request.getParameter("cpfCnpj");
		String numeroConta = request.getParameter("numeroConta");

		model.addAttribute("contas", contaDAO.findBy(cliente, cpfCnpj, numeroConta));
		return new ModelAndView("contas", model.asMap());
	}

	/**
	 * 
	 * @param model
	 *            model
	 * @return um {@link ModelAndView} para redirecionameto para tela de criação
	 *         de novas contas
	 */
	@RequestMapping(value = "novaConta")
	public ModelAndView newConta() {
		return new ModelAndView("contaFormulario");
	}

	/**
	 * Remove uma conta
	 * 
	 * @param model
	 *            model
	 * @param agend
	 *            agend
	 * @return uma requisição para a tela de listagem de agendamentos
	 */
	@RequestMapping("removeConta")
	@Transactional
	public String remove(Model model, Conta conta) {
		contaDAO.delete(contaDAO.findByPk(conta.getPk()));
		return "redirect:contas";
	}

	/**
	 * Insere uma nova conta
	 * 
	 * @param request
	 *            request
	 * @return uma requisição para a ir para tela de listagem de contas
	 */
	@RequestMapping(value = "inserirConta", method = RequestMethod.POST)
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
