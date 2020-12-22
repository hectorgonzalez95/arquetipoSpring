package cl.hector.arqutipo_web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
@Slf4j
public class LoginController {

	@GetMapping(value = "login")
	public ModelAndView login(@RequestParam(required = false) Integer error){
		log.debug("login view request... ");
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",error);
		return mv;
	}

	@GetMapping(value = "navegador")
	public ModelAndView navegador() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
}