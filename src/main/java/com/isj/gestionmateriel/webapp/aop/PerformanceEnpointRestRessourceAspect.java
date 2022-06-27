package com.isj.gestionmateriel.webapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceEnpointRestRessourceAspect {

    @Pointcut("execution(public * com.isj.gestionmateriel.webapp.presentation.rest.ressource.*Resource.*(..))")
    public void anyMethodCall() {
    }

    @Around(value = "anyMethodCall() && !@annotation(Supervision)")
    public Object dureeExecutionAnyMethodeNotAnnoteSupervision(ProceedingJoinPoint joinPoint)
            throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            long duree = end - start;
            PerformanceEnpointRestRessourceAspect.log.info("l'appel au endpoint " + joinPoint.toShortString() + " à durée " + duree + "ms");

        }
    }

}
