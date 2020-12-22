package cl.hector.arqutipo_web.fw.recaptcha;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Getter(PROTECTED)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ReCaptchaFilter extends GenericFilter {

    String secreto;
    String nombreVariableReCaptchaResponse;
    List<RequestMatcher> urlsProtegidas;
    float limite;

    public ReCaptchaFilter(ReCaptchaConfigurationProperties reCaptchaConfigurationProperties) {
        if (reCaptchaConfigurationProperties.getSecreto() == null)
            throw new IllegalArgumentException("No se ha configurado el secreto de reCaptcha");

        this.secreto = reCaptchaConfigurationProperties.getSecreto();
        this.nombreVariableReCaptchaResponse = reCaptchaConfigurationProperties.getNombreVariableReCaptchaResponse();
        this.limite = reCaptchaConfigurationProperties.getLimite();
        this.urlsProtegidas = stream(ofNullable(reCaptchaConfigurationProperties.getUrlsProtegidas()).orElseGet(() -> new String[0]))
                .map(AntPathRequestMatcher::new)
                .collect(toList());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (shouldNotFilter(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

        if (!validarRequest(httpServletRequest, httpServletResponse, filterChain)) return;
        boolean reCaptchaResponse = validarCaptcha(httpServletRequest);
        if (reCaptchaResponse) {
            log.debug("FW3: La respuesta del reCaptcha fue exitosa");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            log.debug("FW3: La respuesta del reCaptcha no fue exitosa");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }

    @Override
    public void destroy() {
        //Do nothing
    }

    protected boolean shouldNotFilter(HttpServletRequest request) {
        return urlsProtegidas.stream().noneMatch(matcher -> matcher.matches(request));

    }

    private boolean validarRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        return null != request.getParameter(nombreVariableReCaptchaResponse);
    }

    protected boolean validarCaptcha(HttpServletRequest httpServletRequest) {
        return ReCaptchaValidator.builder()
                .secreto(secreto)
                .respuestaReCaptchaUsuario(httpServletRequest.getParameter(nombreVariableReCaptchaResponse))
                .ip(obtenerIP(httpServletRequest))
                .limite(limite)
                .build()
                .validate();
    }

    private String obtenerIP(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }
}
/*public class ReCaptchaFilter extends OncePerRequestFilter {

    String secreto;
    String nombreVariableReCaptchaResponse;
    List<RequestMatcher> urlsProtegidas;
    float limite;

    public ReCaptchaFilter(ReCaptchaConfigurationProperties reCaptchaConfigurationProperties) {
        if (reCaptchaConfigurationProperties.getSecreto() == null)
            throw new IllegalArgumentException("No se ha configurado el secreto de reCaptcha");

        this.secreto = reCaptchaConfigurationProperties.getSecreto();
        this.nombreVariableReCaptchaResponse = reCaptchaConfigurationProperties.getNombreVariableReCaptchaResponse();
        this.limite = reCaptchaConfigurationProperties.getLimite();
        this.urlsProtegidas = stream(ofNullable(reCaptchaConfigurationProperties.getUrlsProtegidas()).orElseGet(() -> new String[0]))
                .map(AntPathRequestMatcher::new)
                .collect(toList());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return urlsProtegidas.stream()
                .noneMatch(matcher -> matcher.matches(request));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (!validarRequest(httpServletRequest, httpServletResponse, filterChain)) return;
        boolean reCaptchaResponse = validarCaptcha(httpServletRequest);
        if (reCaptchaResponse) {
            log.debug("La respuesta del reCaptcha fue exitosa");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            log.debug("La respuesta del reCaptcha no fue exitosa");
            //handleInvalidCaptcha(request, response, filterChain);
        }
    }

    private boolean validarRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        if (null == request.getParameter(nombreVariableReCaptchaResponse)) {
            return false;
        }
        return true;
    }

    protected boolean validarCaptcha(HttpServletRequest httpServletRequest) {
        return ReCaptchaValidator.builder()
                .secreto(secreto)
                .respuestaReCaptchaUsuario(httpServletRequest.getParameter(nombreVariableReCaptchaResponse))
                .ip(obtenerIP(httpServletRequest))
                .limite(limite)
                .build()
                .validate();
    }

    private String obtenerIP(HttpServletRequest httpServletRequest){
        String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }

}*/
