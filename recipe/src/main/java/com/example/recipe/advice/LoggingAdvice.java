package com.example.recipe.advice;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAdvice {

    /**
     * - * return type
     * - * package
     * - * class
     * - * method
     * - (..) any number of argument
     */
//    @Pointcut(value="within(com.example.recipe.apis.*)") // Within the package
//    @Pointcut(value="this(com.example.recipe.apis.service.RecipeService)") // Within the package
//    @Pointcut(value="execution(* com.example.recipe.apis.*.*(..))")
    @Pointcut("@annotation(com.example.recipe.annotation.CustomAnnotation)")
    public void advicePointcut() { }

    @Before("advicePointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before method invoked: " + joinPoint.getSignature().getName());
    }

    @After("advicePointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("After method invoked: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "advicePointcut()", returning = "message")
    public void afterReturning(JoinPoint joinPoint, String message) {
        log.info("AfterReturning method invoked: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "advicePointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("AfterThrowing method invoked: " + joinPoint.getSignature().getName());
        log.error(exception.getMessage());
    }

    @Around("advicePointcut()")
    public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] args = joinPoint.getArgs();
        log.info("Around method invoked: " + className + " : " + methodName);

        Object object = joinPoint.proceed();
        log.info(object);
        return object;
    }
}
