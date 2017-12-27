package com.ipman1971.packlink.repository.impl;

import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.repository.AbstractBaseRepository;
import com.ipman1971.packlink.repository.TrackingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcorredera on 22/12/17.
 */
@Repository
public class TrackingRepositoryImpl extends AbstractBaseRepository<String, Tracking>
        implements TrackingRepository<String, Tracking> {
}
