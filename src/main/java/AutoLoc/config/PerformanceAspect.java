package AutoLoc.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);

    @Around("execution(* AutoLoc.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // Exécute la méthode cible

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String methodName = joinPoint.getSignature().getName();

        log.info("⏱️ [PERFORMANCE] Méthode {} exécutée en {} ms", methodName, executionTime);

        if (executionTime > 1000) {
            log.warn("🚨 [ALERTE] La méthode {} a dépassé le seuil de 1000 ms ! (Temps : {} ms)", methodName, executionTime);
        }

        return result;
    }
}