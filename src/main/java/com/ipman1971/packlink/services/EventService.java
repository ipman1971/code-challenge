package com.ipman1971.packlink.services;

import com.ipman1971.packlink.domain.Event;
import com.ipman1971.packlink.domain.Tracking;

/**
 * Created by jcorredera on 24/12/17.
 */
public interface EventService {

    public Event process(String reference);

}
