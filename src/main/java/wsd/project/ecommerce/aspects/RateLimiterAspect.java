package wsd.project.ecommerce.aspects;


import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimiterAspect {

    private final ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    @Around("@annotation(wsd.project.ecommerce.aspects.RateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RateLimit rateLimit = signature.getMethod().getAnnotation(RateLimit.class);

        String methodName = signature.getMethod().getName();
        RateLimiter rateLimiter = limiters.computeIfAbsent(methodName, k -> RateLimiter.create(rateLimit.value()));

        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        } else {
            return new ResponseEntity<>("Rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
        }
    }
}
