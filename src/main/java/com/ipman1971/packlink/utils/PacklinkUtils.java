package com.ipman1971.packlink.utils;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.domain.Tracking;

/**
 * Created by jcorredera on 24/12/17.
 */
public class PacklinkUtils {

    public static boolean isSameReference(Shipment shipment, Tracking tracking) {
        return shipment.getReference().equals(tracking.getReference());
    }

    public static boolean isDiferentReference(Shipment shipment, Tracking tracking) {
        return !shipment.getReference().equals(tracking.getReference());
    }

    public static boolean isSameParcelNumber(Shipment shipment, Tracking tracking) {
        return shipment.getParcels().size()==tracking.getParcels();
    }

    public static int compareWeight(Shipment shipment,Tracking tracking) {
        int shipmentWeigth = shipment.getParcels().stream().map(parcel -> parcel.getWeight()).reduce(0, Integer::sum);
        return Integer.compare(shipmentWeigth,tracking.getWeight());
    }

    public static boolean isAnyNullField(Tracking tracking) {
        return tracking.getStatus()==null || tracking.getWeight()<=0 || tracking.getParcels()<=0
                || tracking.getReference()==null;
    }

}
