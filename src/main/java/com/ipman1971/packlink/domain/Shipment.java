package com.ipman1971.packlink.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jcorredera on 21/12/17.
 */
@ApiModel(description = "model representation for Shipment")
public class Shipment {

    private String reference;
    private List<Parcel> parcels;

    public Shipment() {
        this("ABCD000000", new ArrayList<>());
    }

    public Shipment(String reference) {
        this(reference, new ArrayList<>());
    }

    public Shipment(String reference, List<Parcel> parcels) {
        this.reference = reference;
        this.parcels = parcels;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @ApiModelProperty(name = "reference", dataType = "string", required = true)
    public String getReference() {
        return this.reference;
    }

    public void addParcel(Parcel parcel) {
        parcels.add(parcel);
    }

    public void removeParcel(Parcel parcel) {
        parcels.remove(parcel);
    }

    @ApiModelProperty(name = "parcels", dataType = "array", required = true)
    public List<Parcel> getParcels() {
        return Collections.unmodifiableList(parcels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment)) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equal(reference, shipment.reference) && Objects.equal(parcels, shipment.parcels);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reference, parcels);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).
                add("reference", reference).
                add("parcels", parcels).
                omitNullValues().
                toString();
    }

}
