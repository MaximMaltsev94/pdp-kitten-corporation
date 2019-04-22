package pdp.kitten.corporation.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerAspect {

    private final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);

    private final String METHOD_INVOCATION_FORMAT = "%s : %s ( %s )";

    public void methodInvocationAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder args = new StringBuilder();
        if(ArrayUtils.isNotEmpty(joinPoint.getArgs())) {
            for (Object arg : joinPoint.getArgs()) {
                args.append(arg.toString());
            }
        }
        LOG.info(String.format(METHOD_INVOCATION_FORMAT, className, methodName, args.toString()));
    }
}
