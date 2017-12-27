package com.ipman1971.packlink.utils;

import com.ipman1971.packlink.domain.Parcel;
import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.domain.Tracking;
import org.junit.Test;

import static com.ipman1971.packlink.utils.TrackingRulesUtils.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

public class TrackingRulesUtilsTest {

    @Test
    public void concillationRequestCase_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.DELIVERED,2,10,"ABCD123456");

        assertThat(isConcillationRequestCase(shipment,tracking),is(true));
    }

    @Test
    public void notNeedCase_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.DELIVERED,2,1,"ABCD123456");

        assertThat(isNotNeedCase(shipment,tracking),is(true));
    }

    @Test
    public void incompleteCase_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB,2,10,"ABCD123456");

        assertThat(isIncompleteCase(shipment,tracking),is(true));
    }

    @Test
    public void nullFieldCase_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.DELIVERED,2,0,"ABCD123456");

        assertThat(isNullFieldCase(shipment,tracking),is(true));
    }

    @Test
    public void notFoundCase_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.DELIVERED,2,0,"ABCD000000");

        assertThat(isNotFoundCase(shipment,tracking),is(true));
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