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
	
	@RequestMapping("buscaAgendOuVaiParaForm")
	@Transactional
	public String redirectToSearchOrNewAgend(HttpServletRequest request) {
		String submit = request.getParameter("submit");
		if (submit != null) {
			if(submit.equals("pesquisa")) {
				return "forward:/buscaAgend";
			} else if(submit.equals("novo")) {
				return "redirect:novaAgend";
			}				
		}
		return "redirect:agendamentos";
	}
	
	@RequestMapping(value = "buscaAgend")
	@Transactional
	public ModelAndView search(Model model, HttpServletRequest request) {
		String contaOrig = request.getParameter("contaOrig");
		String contaDest = request.getParameter("contaDest");
		String tipoAgend = request.getParameter("tipoAgend");

		//model.addAttribute("agendamentos", agendDAO.findBy(contaOrig, contaDest, tipoAgend));
		return new ModelAndView("agendamentos", model.asMap());
	}
	
	@RequestMapping(value = "novaAgend")
	public ModelAndView newAgend(Model model) {
		return new ModelAndView("agendFormulario", model.asMap());
	}
	
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
			Calendar dtOperacao = CalendarUtil.getCurrentTime();
			
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

	@RequestMapping("removeAgend")
	@Transactional
	public ModelAndView remove(Model model, AgendamentoTransf agend) {
		agendDAO.delete(agendDAO.findByPk(agend.getPk()));
		model.addAttribute("agendamentos", contaDAO.findAll());
		return new ModelAndView("agendamentos", model.asMap());
	}
	
	@ModelAttribute("tipos")
	public List<TipoAgendamento> tipos() {
		return Arrays.asList(TipoAgendamento.values());
	}
}
