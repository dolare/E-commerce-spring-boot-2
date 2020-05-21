package com.dolare.aspect;

import jdk.nashorn.internal.runtime.ECMAException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    /* AOP:
    * 1. before
    * 2. after
    * 3. circle
    * 4. exception
    * 5. final
    * */
    public static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Around("execution(* com.dolare..service.implement..*.*(..))") // * means all, excution means the body, .. means the package and the children packages, *(..) means all method with any parameters
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("====== start, {}.{}=======",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());

        // start time
        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        long take = end - begin;

        if (take > 3000) {
            log.error("====== end, cost: {} ms =======", take);
        } else if (take > 2000) {
            log.warn("====== end, cost: {} ms =======", take);
        } else {
            log.info("====== end, cost: {} ms =======", take);
        }

        return result;
    }
}
