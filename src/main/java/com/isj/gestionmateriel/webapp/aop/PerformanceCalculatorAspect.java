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
public class PerformanceCalculatorAspect {

    @Around(value ="@annotation(supervision)")
    public Object dureeExecutionAnyMethodeAnnoteSupervision(ProceedingJoinPoint joinPoint, Supervision supervision)
            throws Throwable {
        long maxDuree = supervision.dureeMillis();
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            long duree = end - start;
            if (duree > maxDuree) {
                PerformanceCalculatorAspect.log.info("Attention l'appel à "
                        +joinPoint.toShortString()+" à durée "
                        +duree+"ms soit "
                        +(duree - maxDuree)+"ms de plus qu'attendu");
            }
        }
    }


}
