package com.ipman1971.packlink.aspect;

import com.ipman1971.packlink.domain.Event;
import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.domain.Tracking;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jcorredera on 26/01/17 - 16:17.
 */
@Aspect
@Component
public class RepositoryLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryLoggerAspect.class);

    @Before("execution(public * com.ipman1971.packlink.repository.*Repository.*(..))")
    public void logBeforeEventServiceCall(JoinPoint pjp) throws Throwable {
        Object[] args=pjp.getArgs();
        for(Object arg:args) {
            if(arg instanceof Event) {
                LOGGER.info("[REPOSITORY]: executed method: {}() => {} ",pjp.getSignature().getName(),((Event)arg).toString());
            }
            else if(arg instanceof Tracking) {
                LOGGER.info("[REPOSITORY]: executed method: {}() => {} ",pjp.getSignature().getName(),((Tracking)arg).toString());
            }
            else if(arg instanceof Shipment) {
                LOGGER.info("[REPOSITORY]: executed method: {}() => {} ",pjp.getSignature().getName(),((Shipment)arg).toString());
            }
        }
    }

}
