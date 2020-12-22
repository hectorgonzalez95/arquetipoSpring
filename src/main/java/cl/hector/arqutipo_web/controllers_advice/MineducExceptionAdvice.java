package cl.hector.arqutipo_web.controllers_advice;

import cl.mineduc.framework2.exceptions.MineducException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class MineducExceptionAdvice {

	@ExceptionHandler(MineducException.class)
	public String handleMineducException(MineducException e, RedirectAttributes redirectAttributes)  {
		log.info("HANDLING MineducException... {}",e.getLocalizedMessage());
		redirectAttributes.addFlashAttribute("exception", e);
		return "redirect:/error/mineduc-error";
		
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, RedirectAttributes redirectAttributes)  {
		log.info("HANDLING ... {}",e.getClass().getName());
		log.error("EXCEPCION NO CONTROLADA!!!",e);
		redirectAttributes.addFlashAttribute("exception", e);
		return "redirect:/error/generic-error";
		
	}
}
