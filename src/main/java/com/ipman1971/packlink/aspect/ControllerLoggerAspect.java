package com.ipman1971.packlink.aspect;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.services.EventService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jcorredera on 24/01/17 - 13:17.
 */
@Aspect
@Component
public class ControllerLoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLoggerAspect.class);

    @Autowired
    private EventService eventService;

    @Before("execution(public * com.ipman1971.packlink.controllers.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        Object[] args=pjp.getArgs();
        for(Object arg:args) {
            if(arg instanceof Shipment) {
                LOGGER.info("[CONTROLLER]: data recived => {} ",((Shipment)arg).toString());
            }
            else if(arg instanceof Tracking) {
                Tracking tracking = ((Tracking)arg);
                LOGGER.info("[CONTROLLER]: data recived => {} ",((Tracking)arg).toString());
            }
        }
    }

}
