package com.ipman1971.packlink.events;

import org.springframework.context.ApplicationEvent;

public class PacklinkEvent<TYPE> extends ApplicationEvent {

    private TYPE type;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PacklinkEvent(Object source) {
        super(source);
        type = (TYPE) source;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

}
