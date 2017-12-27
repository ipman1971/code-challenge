package com.ipman1971.packlink.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jcorredera on 22/12/17.
 */
@ApiModel(description = "model representation for Tracking")
public class Tracking {

    public enum Status {
        WAITING_IN_HUB, DELIVERED;
    }

    private Status status;
    private int parcels;
    private int weight;
    private String reference;

    public Tracking() {
        this(Status.WAITING_IN_HUB,0,0,"####000000");
    }

    public Tracking(Status status, int parcels, int weight, String reference) {
        this.status = status;
        this.parcels = parcels;
        this.weight = weight;
        this.reference = reference;
    }

    @ApiModelProperty(name = "status", dataType = "string", required = true)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ApiModelProperty(name = "parcels", dataType = "int", required = true)
    public int getParcels() {
        return parcels;
    }

    public void setParcels(int parcels) {
        this.parcels = parcels;
    }

    @ApiModelProperty(name = "weight", dataType = "int", required = true)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @ApiModelProperty(name = "reference", dataType = "string", required = true)
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tracking)) return false;
        Tracking tracking = (Tracking) o;
        return parcels == tracking.parcels && weight == tracking.weight && status == tracking.status &&
                Objects.equal(reference, tracking.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, parcels, weight, reference);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).
                add("parcels", parcels).
                add("status", status).
                add("weight", weight).
                add("reference", reference).
                omitNullValues().
                toString();
    }

}
