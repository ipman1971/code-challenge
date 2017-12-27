package com.ipman1971.packlink.utils;

import com.ipman1971.packlink.domain.Parcel;
import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.domain.Tracking;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by jcorredera on 24/12/17.
 */
public class PacklinkUtilsTest {

    @Test
    public void isSameReference_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 2, "ABCD123456");

        assertThat(PacklinkUtils.isSameReference(shipment, tracking),is(true));
    }

    @Test
    public void isSameReference_false() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 2, "ABCD000000");

        assertThat(PacklinkUtils.isSameReference(shipment, tracking),is(false));
    }

    @Test
    public void isDiferentReference_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 2, "EFGH123456");

        assertThat(PacklinkUtils.isDiferentReference(shipment, tracking),is(true));
    }

    @Test
    public void isDiferentReference_false() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 2, "ABCD123456");

        assertThat(PacklinkUtils.isDiferentReference(shipment, tracking),is(false));
    }

    @Test
    public void isSameParcelNumber_true() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 2, "ABCD123456");

        assertThat(PacklinkUtils.isSameParcelNumber(shipment, tracking),is(true));
    }

    @Test
    public void isSameParcelNumber_false() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 4, 2, "ABCD123456");

        assertThat(PacklinkUtils.isSameParcelNumber(shipment, tracking),is(false));
    }

    @Test
    public void compareWeight_le() throws Exception {
        Shipment shipment = createShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 200, "ABCD123456");

        assertThat(PacklinkUtils.compareWeight(shipment, tracking),is(-1));
    }

    @Test
    public void compareWeight_gt() throws Exception {
        Shipment shipment = createWeightShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 10, "ABCD123456");

        assertTrue(PacklinkUtils.compareWeight(shipment, tracking)>=0);
    }

    @Test
    public void isAnyFieldNull_true() throws Exception {
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 0, 10, "ABCD123456");

        assertThat(PacklinkUtils.isAnyNullField(tracking),is(true));
    }

    @Test
    public void isAnyFieldNull_false() throws Exception {
        Shipment shipment = createWeightShipment();
        Tracking tracking = new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 10, "ABCD123456");

        assertThat(PacklinkUtils.isAnyNullField(tracking),is(false));
    }

    private Shipment createShipment() {
        Parcel parcel1 = new Parcel(1, 10, 10, 10);
        Parcel parcel2 = new Parcel(2, 20, 20, 20);
        Shipment shipment = new Shipment("ABCD123456");
        shipment.addParcel(parcel1);
        shipment.addParcel(parcel2);
        return shipment;
    }

    private Shipment createWeightShipment() {
        Parcel parcel1 = new Parcel(100, 10, 10, 10);
        Parcel parcel2 = new Parcel(200, 20, 20, 20);
        Shipment shipment = new Shipment("ABCD123456");
        shipment.addParcel(parcel1);
        shipment.addParcel(parcel2);
        return shipment;
    }

}