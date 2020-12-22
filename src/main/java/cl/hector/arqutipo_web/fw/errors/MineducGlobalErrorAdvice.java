package cl.hector.arqutipo_web.fw.errors;

import cl.hector.arqutipo_web.fw.interceptors.CommonModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class MineducGlobalErrorAdvice {

    @Autowired
    private CommonModelService commonService;

    @ExceptionHandler({Exception.class})
    public ModelAndView handleErrorException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex, RedirectAttributes redirectAttributes) {

        ModelAndView mv =  new ModelAndView("errors/mineduc-exception");
        String uuid = UUID.randomUUID().toString();
        if (ex != null) {
            log.error("FW3: Error no controlado. id {}", uuid, ex);
        } else {
            log.error("FW3: Error no controlado. id {}", uuid);
        }
        if (commonService != null){
            try {
                commonService.fillModel(request, response,handler,mv);
            } catch (Exception e) {
                log.error("FW3: Error llamando a commonService.fillModel. id {}",uuid, e);
            }
        }
        mv.addObject("uuid",uuid);
        mv.addObject("exception",ex);
        return mv;
    }
}
