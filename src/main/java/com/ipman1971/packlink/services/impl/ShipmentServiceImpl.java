package com.ipman1971.packlink.services.impl;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;
import com.ipman1971.packlink.repository.ShipmentRepository;
import com.ipman1971.packlink.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jcorredera on 24/12/17.
 */
@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository repository;

    @Override
    public String register(Shipment shipment) throws PacklinkInternalOperationException {
        if (isNotValidShipment(shipment)) {
            throw new PacklinkInternalOperationException("shipment is null or empty");
        }
        repository.create(shipment.getReference(), shipment,true);
        return shipment.getReference();
    }

    private boolean isNotValidShipment(Shipment shipment) {
        return shipment == null || shipment.getReference().isEmpty();
    }

}
