package com.ipman1971.packlink.repository.impl;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.repository.AbstractBaseRepository;
import com.ipman1971.packlink.repository.ShipmentRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcorredera on 22/12/17.
 */
@Repository
public class ShipmentRepositoryImpl extends AbstractBaseRepository<String, Shipment>
        implements ShipmentRepository<String, Shipment> {
}
