package cl.hector.arqutipo_web.fw.interceptors;

import is.tagomor.woothee.Classifier;
import lombok.extern.slf4j.Slf4j;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class UserAgentInterceptor extends HandlerInterceptorAdapter {

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        Map r = Classifier.parse(request.getHeader("User-Agent"));
        ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
        int majorVersion = 0;
        String browserName = agent.getName();
        OperatingSystem operatingSystem = agent.getOperatingSystem();
        log.debug("FW3: Sistema operativo {} version {}", operatingSystem.getName(), operatingSystem.getVersionNumber().toVersionString());
        if (!StringUtils.isEmpty(agent.getVersionNumber().getMajor())) {
            majorVersion = Integer.parseInt(agent.getVersionNumber().getMajor());
            log.info("FW3: Navegador {} version {}", browserName, majorVersion);
        }

        if (mv != null) {
            if (!(request.getSession() == null)) {
                mv.addObject("clientUserAgentName", browserName);
                mv.addObject("clientUserAgentVersion", majorVersion);
            }
        }

        if (browserName.equals("IE") && majorVersion <= 10) {
            log.debug("FW3: UserAgentInterceptor ha encontrado un problema --> Navegador no soportado");
            assert mv != null;
            mv.setViewName("/login/navegador");
        }
    }
}
