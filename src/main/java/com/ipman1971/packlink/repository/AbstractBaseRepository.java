package com.ipman1971.packlink.repository;

import com.ipman1971.packlink.events.PacklinkEvent;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by jcorredera on 23/12/17.
 */
public abstract class AbstractBaseRepository<KEY, ENTITY> implements IGenericRepository<KEY, ENTITY>, ApplicationEventPublisherAware {

    private Map<KEY, ENTITY> table;
    protected ApplicationEventPublisher publisher;

    public AbstractBaseRepository() {
        table = new ConcurrentHashMap<KEY, ENTITY>();
    }

    @Override
    public ENTITY findOne(KEY key) {
        if(table.containsKey(key)) {
            return table.get(key);
        }
        return null;
    }

    @Override
    public List<ENTITY> findAll() {
        return Collections.unmodifiableList(table.values().stream().collect(Collectors.toList()));
    }

    @Override
    public void create(KEY key, ENTITY entity, boolean active) {
        table.putIfAbsent(key, entity);
        if(active) {
            publish(new PacklinkEvent<ENTITY>(entity));
        }
    }

    @Override
    public ENTITY update(KEY key, ENTITY entity, boolean active) {
        if (table.containsKey(key)) {
            table.put(key, entity);
            if(active) {
                publish(new PacklinkEvent<ENTITY>(entity));
            }
        }
        else {
            create(key,entity,active);
        }
        return entity;
    }

    @Override
    public void delete(KEY key) {
        if (table.containsKey(key)) {
            table.remove(key);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }

    protected void publish(PacklinkEvent packlinkEvent) {
        publisher.publishEvent(packlinkEvent);
    }

}
