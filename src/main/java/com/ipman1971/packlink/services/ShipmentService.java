package com.ipman1971.packlink.services;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;

/**
 * Created by jcorredera on 24/12/17.
 */
public interface ShipmentService {

    public String register(Shipment shipment) throws PacklinkInternalOperationException;

}
