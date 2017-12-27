package com.ipman1971.packlink.services.impl;

import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;
import com.ipman1971.packlink.repository.TrackingRepository;
import com.ipman1971.packlink.services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jcorredera on 24/12/17.
 */
@Service
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    private TrackingRepository repository;

    @Override
    public String push(Tracking tracking) throws PacklinkInternalOperationException {
        if(isNotValidShipment(tracking)) {
            throw new PacklinkInternalOperationException("tracking is null or empty");
        }
        repository.update(tracking.getReference(),tracking, true);
        return tracking.getReference();
    }

    private boolean isNotValidShipment(Tracking tracking) {
        return tracking == null || tracking.getReference().isEmpty();
    }

}
