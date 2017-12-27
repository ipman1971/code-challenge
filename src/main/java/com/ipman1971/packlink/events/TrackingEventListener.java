package com.ipman1971.packlink.events;

import com.ipman1971.packlink.domain.Event;
import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TrackingEventListener {

    @Autowired
    private EventService eventService;

    @Async
    @EventListener
    public Event handleTrackingEvent(PacklinkEvent trackingEvent) {
        Event event = null;
        if(trackingEvent!=null && (trackingEvent.getType() instanceof Tracking)) {
            Tracking tracking=(Tracking)trackingEvent.getType();
            event= eventService.process(tracking.getReference());
        }
        return event;
    }

}
