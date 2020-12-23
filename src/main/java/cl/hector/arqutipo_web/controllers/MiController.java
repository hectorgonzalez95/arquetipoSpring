package cl.hector.arqutipo_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("mi")
@Slf4j
public class MiController {

	@GetMapping(value = "mi2")
	public ModelAndView prueba(){		
		ModelAndView mv = new ModelAndView();		
		String bbb = "bbb";
		mv.addObject("bbb", bbb);		
		return new ModelAndView();	
	}
}
