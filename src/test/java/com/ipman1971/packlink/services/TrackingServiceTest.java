package com.ipman1971.packlink.services;

import com.ipman1971.packlink.domain.Tracking;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class TrackingServiceTest {

    @Autowired
    private TrackingService service;

    @Test
    public void push() throws Exception {
        Tracking tracking = new Tracking(Tracking.Status.DELIVERED,4,560,"ABCD123456");

        String reference = service.push(tracking);
        assertThat(reference,is(equalTo("ABCD123456")));
    }

}