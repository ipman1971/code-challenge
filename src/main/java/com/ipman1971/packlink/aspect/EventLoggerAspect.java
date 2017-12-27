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
 * Created by jcorredera on 24/01/17 - 13:17.
 */
@Aspect
@Component
public class EventLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventLoggerAspect.class);

    @AfterReturning(
            pointcut = "execution(public * com.ipman1971.packlink.events.TrackingEventListener.handleTrackingEvent(..))",
            returning = "event")
    public void logAfterReturning(JoinPoint joinPoint, Object event) {
        if (event != null) {
            LOGGER.info("[EVENT]: generated event => {}", event.toString());
        }
    }

}
