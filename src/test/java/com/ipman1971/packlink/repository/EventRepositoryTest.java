package com.ipman1971.packlink.repository;

import com.ipman1971.packlink.domain.Event;
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
public class EventRepositoryTest {

    @Autowired
    private EventRepository repository;

    @Test
    public void create() throws Exception {
        Event event = createEvent();
        repository.create(event.getReference(), event, false);

        Event oneEvent = (Event) repository.findOne(event.getReference());

        assertThat(event, equalTo(oneEvent));
    }

    @Test
    public void findOne() throws Exception {
        Event event = createEvent();
        repository.create(event.getReference(), event, false);

        Event oneEvent = (Event) repository.findOne(event.getReference());

        assertThat(event, equalTo(oneEvent));
    }

    @Test
    public void notFindOne() throws Exception {
        Event event = createEvent();
        repository.create(event.getReference(), event, false);

        Event otherEvent = (Event)repository.findOne("####");

        assertThat(otherEvent,is(nullValue()));
    }

    @Test
    public void findAll() throws Exception {
        Event event = createEvent();
        repository.create(event.getReference(), event, false);

        assertThat(repository.findAll().size(), equalTo(1));
    }

    @Test
    public void update() throws Exception {
        Event event = createEvent();
        repository.create(event.getReference(), event, false);


        event.setStatus(Event.Status.NOT_FOUND);
        repository.update(event.getReference(), event, false);

        Event oneEvent = (Event) repository.findOne(event.getReference());

        assertThat(oneEvent.getStatus(), equalTo(Event.Status.NOT_FOUND));
    }

    @Test
    public void delete() throws Exception {
        Event event = createEvent();
        repository.create(event.getReference(), event, false);

        repository.delete(event.getReference());

        assertThat(repository.findAll().size(), is(equalTo(0)));

    }

    private Event createEvent() {
        return new Event(Event.Status.CONCILLIATION_REQUEST, "ABCD123456");
    }

}