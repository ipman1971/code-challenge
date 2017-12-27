package com.ipman1971.packlink.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jcorredera on 23/12/17.
 */
@ApiModel(description = "model representation for Event")
public class Event {

    public enum Status {
        CONCILLIATION_REQUEST, NOT_NEEDED, INCOMPLETE, NOT_FOUND
    }

    private Status status;
    private String reference;


    public Event(Status status, String reference) {
        this.status = status;
        this.reference = reference;
    }

    private Event() {
        this(Status.NOT_FOUND, "####000000");
    }

    @ApiModelProperty(name = "status", dataType = "string", required = true)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ApiModelProperty(name = "referencia", dataType = "string", required = true)
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return status == event.status && Objects.equal(reference, event.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, reference);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).
                add("status", status).
                add("reference", reference).
                omitNullValues().
                toString();
    }

}
