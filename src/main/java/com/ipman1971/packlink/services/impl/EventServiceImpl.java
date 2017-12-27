package com.ipman1971.packlink.services.impl;

import com.ipman1971.packlink.domain.Event;
import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;
import com.ipman1971.packlink.repository.EventRepository;
import com.ipman1971.packlink.repository.ShipmentRepository;
import com.ipman1971.packlink.repository.TrackingRepository;
import com.ipman1971.packlink.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ipman1971.packlink.utils.TrackingRulesUtils.*;

/**
 * Created by jcorredera on 24/12/17.
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event process(String reference) {
        Event event = null;
        Shipment shipment = Optional.ofNullable((Shipment)shipmentRepository.findOne(reference))
                .orElse(new Shipment());
        Tracking tracking = Optional.ofNullable((Tracking)trackingRepository.findOne(reference))
                .orElse(new Tracking());

        if(isConcillationRequestCase(shipment,tracking)) {
            event = createEvent(new Event(Event.Status.CONCILLIATION_REQUEST,tracking.getReference()));
        }
        else if(isNotNeedCase(shipment,tracking)) {
            event = createEvent(new Event(Event.Status.NOT_NEEDED,tracking.getReference()));
        }
        else if(isIncompleteCase(shipment,tracking) ) {
            event = createEvent(new Event(Event.Status.INCOMPLETE,tracking.getReference()));
        }
        else if(isNullFieldCase(shipment,tracking)) {
            event = createEvent(new Event(Event.Status.INCOMPLETE,tracking.getReference()));
        }
        else if(isNotFoundCase(shipment,tracking)) {
            event = createEvent(new Event(Event.Status.NOT_FOUND, tracking.getReference()));
        }
        else {
            throw new PacklinkInternalOperationException("tracking rule not found....");
        }
        return event;
    }

    private Event createEvent(Event event) {
        eventRepository.create(event.getReference(),event, true);
        return event;
    }

}
