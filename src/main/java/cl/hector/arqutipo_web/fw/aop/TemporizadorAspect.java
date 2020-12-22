package cl.hector.arqutipo_web.fw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TemporizadorAspect {
    @Around(value = "@annotation(cl.mineduc.framework3starter.contraints.Temporizador)")
    public Object medicionTiempoejecucion(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("FW3: {} ejecutado en {}ms",joinPoint.getSignature(),executionTime);
        return proceed;
    }
}
