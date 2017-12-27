package com.ipman1971.packlink.utils;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.domain.Tracking;

import static com.ipman1971.packlink.utils.PacklinkUtils.*;

public class TrackingRulesUtils {

    public static boolean isConcillationRequestCase(Shipment shipment, Tracking tracking) {
        return isSameReference(shipment, tracking) && isSameParcelNumber(shipment, tracking)
                && (compareWeight(shipment, tracking) < 0) && (tracking.getStatus() == Tracking.Status.DELIVERED);
    }

    public static boolean isNotNeedCase(Shipment shipment, Tracking tracking) {
        return isSameReference(shipment, tracking) && isSameParcelNumber(shipment, tracking)
                && (compareWeight(shipment, tracking) >= 0) && (tracking.getStatus() == Tracking.Status.DELIVERED);
    }

    public static boolean isIncompleteCase(Shipment shipment, Tracking tracking) {
        return isSameReference(shipment, tracking) && (tracking.getStatus() != Tracking.Status.DELIVERED);
    }

    public static boolean isNullFieldCase(Shipment shipment, Tracking tracking) {
        return isSameReference(shipment, tracking) && isAnyNullField(tracking) ;
    }

    public static boolean isNotFoundCase(Shipment shipment, Tracking tracking) {
        return isDiferentReference(shipment,tracking);
    }

}
