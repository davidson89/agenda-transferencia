package br.com.test.rf.agendaTransf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author "davidson.rodrigues"
 *
 * @created 31 de out de 2015
 */
@Controller
public class HomeController {

	@RequestMapping(value = {"/", "index", "home"}) 
	public ModelAndView index() { 
		return new ModelAndView("index");
	}
	
}
