package cl.hector.arqutipo_web.controllers;

import cl.hector.arqutipo_web.models.auth.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("miRamaIntelliJ")
public class MiRamaIntelliJController {

	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		return new ModelAndView();
	}

	@GetMapping(value = "home/index")
	public ModelAndView home(@RequestParam(required = false) Integer error){
		ModelAndView mv = new ModelAndView();
        mv.addObject("userId", this.getCurrentUser().getLogin() );	
		return mv;
	}
	
	private Usuario getCurrentUser() {
		Usuario loggedUser = null;
		if(!(SecurityContextHolder.getContext().getAuthentication().getDetails()==null)){
			loggedUser = (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
		}else{
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal()==null)){
				loggedUser = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		return loggedUser;
	}
}