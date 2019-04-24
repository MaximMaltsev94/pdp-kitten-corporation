package pdp.kitten.corporation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProfilingAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfilingAspect.class);

    @Pointcut("within(pdp.kitten.corporation.controller.*)")
    private void controllersPointcut() {

    }

    @Around("controllersPointcut()")
    public Object profileMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("--------------------------------------------------\\");
        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        LOGGER.info(String.format("PROFILER: method `%s` took %s ms", joinPoint.getSignature().getName(), System.currentTimeMillis() - start));
        LOGGER.info("--------------------------------------------------/");
        return returnValue;
    }
}
