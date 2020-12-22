package cl.hector.arqutipo_web.fw.interceptors;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Getter
@Setter
public class CommonModelService {

    private DeviceResolverHandlerInterceptor deviceInterceptor = new DeviceResolverHandlerInterceptor();
    private SitePreferenceHandlerInterceptor siteInterceptor = new SitePreferenceHandlerInterceptor();

    private String developIPs = null;
    private String[] ipAddresses = null;

    public void fillModel(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        String contentType = response.getContentType();
        getDeviceInterceptor().preHandle(request, response, handler);
        getSiteInterceptor().preHandle(request, response, handler);

        if (StringUtils.isEmpty(contentType)) {
            response.setContentType("text/html; charset=UTF-8");
        }
        setSecurityHeader(response);
        Enumeration<String> params = request.getParameterNames();
        Map<String, Object> requestModel = new HashMap<>();
        while (params.hasMoreElements()) {
            String parameterName = params.nextElement();
            requestModel.put(parameterName, request.getParameter(parameterName));
            log.debug("FW3: Request: nombreParametro = {} Valor= {}", parameterName, request.getParameter(parameterName));
        }
        if (mv != null) {
            mv.addObject("currDate", LocalDateTime.now());
            mv.addObject("request", requestModel);
            mv.addObject("context", request.getContextPath());
            mv.addObject("ip", getClientIP(request));
            mv.addObject("isDevelopment", checkIsDevelopment(getClientIP(request)));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getDetails() != null) {
                mv.addObject("currentUser", auth.getDetails());
            }
            Device device = DeviceUtils.getCurrentDevice(request);
            checkDevice(mv, device);
        }
    }

    private void setSecurityHeader(HttpServletResponse response) {
        response.setHeader("X-XSS-Protection", "1");
        response.setHeader("mode", "block");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Security-Policy", "frame-ancestors *");
        response.setHeader("X-Frame-Options", "sameorigin");
        response.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
        response.setHeader("X-Permitted-Cross-Domain-Policies", "none");
    }

    private void checkDevice(ModelAndView mv, Device device) {
        if (device != null) {
            if (device.isMobile()) {
                mv.addObject("isMobile", true);
                mv.addObject("isTablet", false);
                mv.addObject("isNormal", false);
            } else if (device.isTablet()) {
                mv.addObject("isMobile", false);
                mv.addObject("isTablet", true);
                mv.addObject("isNormal", false);
            } else {
                mv.addObject("isMobile", false);
                mv.addObject("isTablet", false);
                mv.addObject("isNormal", true);
            }
        }
    }

    public String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public boolean checkIsDevelopment(String clientIP) {
        if (ipAddresses == null && getDevelopIPs() != null) {
            ipAddresses = getDevelopIPs().split(",|\\s");
        }
        boolean notEmptyIP = !StringUtils.isEmpty(clientIP);
        for (int i = 0; notEmptyIP && ipAddresses != null && i < ipAddresses.length; i++) {
            if (!StringUtils.isEmpty(ipAddresses[i]) && clientIP.equals(ipAddresses[i].trim())) {
                return true;
            }
        }
        return false;
    }
}
