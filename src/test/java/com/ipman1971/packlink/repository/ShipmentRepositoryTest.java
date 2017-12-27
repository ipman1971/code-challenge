package com.ipman1971.packlink.repository;

import com.ipman1971.packlink.domain.Parcel;
import com.ipman1971.packlink.domain.Shipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by jcorredera on 23/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipmentRepositoryTest {

    @Autowired
    private ShipmentRepository repository;

    @Test
    public void create() throws Exception {
        Shipment shipment = createShipment();
        repository.create(shipment.getReference(), shipment, false);

        Shipment oneShipment = (Shipment) repository.findOne(shipment.getReference());

        assertThat(shipment, equalTo(oneShipment));
    }

    @Test
    public void findOne() throws Exception {
        Shipment shipment = createShipment();
        repository.create(shipment.getReference(), shipment, false);

        Shipment oneShipment = (Shipment) repository.findOne(shipment.getReference());

        assertThat(shipment, equalTo(oneShipment));
    }

    @Test
    public void notFindOne() throws Exception {
        Shipment shipment = createShipment();
        repository.create(shipment.getReference(), shipment, false);

        Shipment otherShipment = (Shipment)repository.findOne("####");

        assertThat(otherShipment,is(nullValue()));
    }

    @Test
    public void findAll() throws Exception {
        Shipment shipment = createShipment();
        repository.create(shipment.getReference(), shipment, false);

        assertThat(repository.findAll().size(), equalTo(1));
    }

    @Test
    public void update() throws Exception {
        Shipment shipment = createShipment();
        repository.create(shipment.getReference(), shipment, false);


        shipment.addParcel(new Parcel(3, 30, 30, 30));
        repository.update(shipment.getReference(), shipment, false);

        Shipment oneShipment = (Shipment) repository.findOne(shipment.getReference());

        assertThat(oneShipment.getParcels().size(), equalTo(3));
    }

    @Test
    public void delete() throws Exception {
        Shipment shipment = createShipment();
        repository.create(shipment.getReference(), shipment, false);

        repository.delete(shipment.getReference());

        assertThat(repository.findAll().size(), is(equalTo(0)));

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