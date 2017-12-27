package com.ipman1971.packlink.services;

import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.exceptions.PacklinkInternalOperationException;

/**
 * Created by jcorredera on 24/12/17.
 */
public interface TrackingService {

    public String push(Tracking tracking) throws PacklinkInternalOperationException;

}
