package br.com.test.rf.agendaTransf.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.test.rf.agendaTransf.dao.AgendamentoTransfDAO;
import br.com.test.rf.agendaTransf.dao.ContaDAO;
import br.com.test.rf.agendaTransf.domain.AgendamentoTransf;
import br.com.test.rf.agendaTransf.domain.Conta;
import br.com.test.rf.agendaTransf.enumx.TipoAgendamento;
import br.com.test.rf.agendaTransf.util.CalendarUtil;

/**
 * Controller responsável por resolver as requisições das telas de criação e
 * listagem de agendamentos
 * 
 * @author "davidson.rodrigues"
 *
 * @created 1 de nov de 2015
 */
@Controller
public class AgendamentoController {

	@Autowired
	private AgendamentoTransfDAO agendDAO;

	@Autowired
	private ContaDAO contaDAO;

	@RequestMapping("agendamentos")
	@Transactional
	public ModelAndView list(Model model) {
		model.addAttribute("agendamentos", agendDAO.findAll());
		return new ModelAndView("agendamentos", model.asMap());
	}

	/**
	 * Verifica qual foi a requisição realizada (pesquisa ou novo) e
	 * redirecional para a {@link RequestMapping} correta.
	 * 
	 * @param request
	 *            request
	 * @return uma nova requisição
	 */
	@RequestMapping("buscaAgendOuVaiParaForm")
	@Transactional
	public String redirectToSearchOrNewAgend(HttpServletRequest request) {
		String submit = request.getParameter("submit");
		if (submit != null) {
			if (submit.equals("pesquisa")) {
				return "forward:/buscaAgend";
			} else if (submit.equals("novo")) {
				return "redirect:novaAgend";
			}
		}
		return "redirect:agendamentos";
	}

	/**
	 * Filtra os agendamentos de acordo com os parâmetros da tela
	 * 
	 * @param model
	 *            model
	 * @param request
	 *            request
	 * @return uma {@link ModelAndView} com os agendamentos filtrados
	 */
	@RequestMapping(value = "buscaAgend")
	@Transactional
	public ModelAndView search(Model model, HttpServletRequest request) {
		String contaOrig = request.getParameter("contaOrig");
		String contaDest = request.getParameter("contaDest");
		String tipoAgend = request.getParameter("tipoAgend");
		String dtOperacao = request.getParameter("dtOperacao");
		String dtTransf = request.getParameter("dtTransf");

		Calendar dataOp = null;
		Calendar dataTransf = null;

		TipoAgendamento tipo = TipoAgendamento.valueOf(tipoAgend);
		try {
			if (!dtOperacao.isEmpty()) {
				dataOp = CalendarUtil.getCalendarByDefaultFormat(dtOperacao);
			}
			if (!dtTransf.isEmpty()) {
				dataTransf = CalendarUtil.getCalendarByDefaultFormat(dtTransf);
			}
		} catch (ParseException e) {
		}

		List<AgendamentoTransf> agendamentos = agendDAO.findBy(contaOrig, contaDest, tipo, dataOp, dataTransf);
		model.addAttribute("agendamentos", agendamentos);
		return new ModelAndView("agendamentos", model.asMap());
	}

	/**
	 * 
	 * @param model
	 *            model
	 * @return um {@link ModelAndView} para redirecionameto para tela de criação
	 *         de novos agendamentos
	 */
	@RequestMapping(value = "novaAgend")
	public ModelAndView newAgend(Model model) {
		return new ModelAndView("agendFormulario", model.asMap());
	}

	/**
	 * Insere um novo agendamento
	 * 
	 * @param request
	 *            request
	 * @return uma requisição para a ir para tela de listagem de agendamentos
	 */
	@RequestMapping(value = "inserirAgend")
	@Transactional
	public String inserirAgend(HttpServletRequest request) {
		String contaOrig = request.getParameter("contaOrig");
		String contaDest = request.getParameter("contaDest");
		String tipoAgend = request.getParameter("tipoAgend");
		String valor = request.getParameter("valor");
		String dtTransf = request.getParameter("dtTransf");

		try {
			Conta contaOrigem = contaDAO.findByNumero(contaOrig);
			Conta contaDestino = contaDAO.findByNumero(contaDest);
			TipoAgendamento tipo = TipoAgendamento.valueOf(tipoAgend);
			BigDecimal valorTransf = new BigDecimal(valor);
			Calendar dtTransferencia = CalendarUtil.getCalendarByDefaultFormat(dtTransf);
			Calendar dtOperacao = CalendarUtil.getCurrentTruncDate();

			AgendamentoTransf agendamento = new AgendamentoTransf();
			agendamento.setContaOrigem(contaOrigem);
			agendamento.setContaDestino(contaDestino);
			agendamento.setTipoAgendamento(tipo);
			agendamento.setValor(valorTransf);
			agendamento.setDataOperacao(dtOperacao);
			agendamento.setDataTransferencia(dtTransferencia);

			agendDAO.save(agendamento);
		} catch (ParseException e) {
		}

		return "redirect:agendamentos";
	}

	/**
	 * Remove um agendamento
	 * 
	 * @param model
	 *            model
	 * @param agend
	 *            agend
	 * @return uma requisição para a tela de listagem de agendamentos
	 */
	@RequestMapping("removeAgend")
	@Transactional
	public String remove(Model model, AgendamentoTransf agend) {
		agendDAO.delete(agendDAO.findByPk(agend.getPk()));
		return "redirect:agendamentos";
	}

	/**
	 * 
	 * @return uma {@link List} de enums {@link TipoAgendamento}, para as telas
	 */
	@ModelAttribute("tipos")
	public List<TipoAgendamento> tipos() {
		return Arrays.asList(TipoAgendamento.values());
	}
}
