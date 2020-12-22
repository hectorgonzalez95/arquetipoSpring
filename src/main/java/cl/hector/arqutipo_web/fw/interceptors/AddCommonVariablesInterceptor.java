package cl.hector.arqutipo_web.fw.interceptors;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Setter
@Getter
public class AddCommonVariablesInterceptor extends HandlerInterceptorAdapter {
    private CommonModelService commonModelService;

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(httpServletRequest, httpServletResponse, handler, modelAndView);
        if (commonModelService != null) {
            commonModelService.fillModel(httpServletRequest, httpServletResponse, handler, modelAndView);
        }
    }
}
