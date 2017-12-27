package com.ipman1971.packlink.repository.impl;

import com.ipman1971.packlink.domain.Event;
import com.ipman1971.packlink.repository.AbstractBaseRepository;
import com.ipman1971.packlink.repository.EventRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcorredera on 23/12/17.
 */
@Repository
public class EventRepositoryImpl extends AbstractBaseRepository<String, Event>
        implements EventRepository<String, Event> {
}
