package com.ipman1971.packlink.repository;

import com.ipman1971.packlink.domain.Tracking;
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
public class TrackingRepositoryTest {

    @Autowired
    private TrackingRepository repository;

    @Test
    public void create() throws Exception {
        Tracking tracking = createTracking();
        repository.create(tracking.getReference(), tracking, false);

        Tracking oneTracking = (Tracking) repository.findOne(tracking.getReference());

        assertThat(tracking, equalTo(oneTracking));
    }

    @Test
    public void findOne() throws Exception {
        Tracking tracking = createTracking();
        repository.create(tracking.getReference(), tracking, false);

        Tracking oneTracking = (Tracking) repository.findOne(tracking.getReference());

        assertThat(tracking, equalTo(oneTracking));
    }

    @Test
    public void notFindOne() throws Exception {
        Tracking tracking = createTracking();
        repository.create(tracking.getReference(), tracking, false);

        Tracking otherTracking=(Tracking)repository.findOne("####");

        assertThat(otherTracking,is(nullValue()));
    }

    @Test
    public void findAll() throws Exception {
        Tracking tracking = createTracking();
        repository.create(tracking.getReference(), tracking, false);

        assertThat(repository.findAll().size(), equalTo(1));
    }

    @Test
    public void update() throws Exception {
        Tracking tracking = createTracking();
        repository.create(tracking.getReference(), tracking, false);


        tracking.setStatus(Tracking.Status.DELIVERED);
        repository.update(tracking.getReference(), tracking, false);

        Tracking oneTracking = (Tracking) repository.findOne(tracking.getReference());

        assertThat(oneTracking.getStatus(), equalTo(Tracking.Status.DELIVERED));
    }

    @Test
    public void delete() throws Exception {
        Tracking tracking = createTracking();
        repository.create(tracking.getReference(), tracking, false);

        repository.delete(tracking.getReference());

        assertThat(repository.findAll().size(), is(equalTo(0)));

    }

    private Tracking createTracking() {
        return new Tracking(Tracking.Status.WAITING_IN_HUB, 2, 2, "ABCD123456");
    }

}