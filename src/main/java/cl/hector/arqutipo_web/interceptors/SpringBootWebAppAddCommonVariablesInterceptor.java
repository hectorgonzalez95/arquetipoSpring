package cl.hector.arqutipo_web.interceptors;

import cl.hector.arqutipo_web.services.SpringBootWebAppCommonModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class SpringBootWebAppAddCommonVariablesInterceptor extends HandlerInterceptorAdapter{

	private SpringBootWebAppCommonModelService commonService;
	
	// Getters
	public SpringBootWebAppCommonModelService getCommonService() {return commonService;}

	// Setters
	public void setCommonService(SpringBootWebAppCommonModelService v) {this.commonService = v;}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		// Call super first of all
		super.postHandle(request, response, handler, mv);
		log.debug("adding SpringBootWebApp common model service");
		if (getCommonService() != null){
			getCommonService().fillModel(request,response,handler,mv);
			log.debug("request model filled!");
		}
	}
}
