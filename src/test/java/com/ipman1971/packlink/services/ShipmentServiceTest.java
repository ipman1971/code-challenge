package com.ipman1971.packlink.services;

import com.ipman1971.packlink.domain.Parcel;
import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.repository.ShipmentRepository;
import com.ipman1971.packlink.services.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipmentServiceTest {

    @Autowired
    private ShipmentService service;

    @Test
    public void register() throws Exception {
        Shipment shipment = createShipment();

        String reference = service.register(shipment);
        assertThat(reference,is(equalTo("ABCD123456")));
    }

    private Shipment createShipment() {
        Parcel parcel1 = new Parcel(1, 10, 10, 10);
        Parcel parcel2 = new Parcel(2, 20, 20, 20);
        Shipment shipment = new Shipment("ABCD123456");
        shipment.addParcel(parcel1);
        shipment.addParcel(parcel2);
        return shipment;
    }

}