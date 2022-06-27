package com.isj.gestionmateriel.webapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before("execution(public * com.isj.gestionmateriel.webapp.presentation.rest.ressource.*Resource.*(..))")
    public void log(JoinPoint joinPoint) {
        LogAspect.log.info("Appel de la methode "
                + joinPoint.toShortString() + " avec "
                + joinPoint.getArgs().length + " paramètres");
    }

}