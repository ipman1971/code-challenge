package com.ipman1971.packlink.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jcorredera on 21/12/17.
 */
@ApiModel(description = "model representation for Parcel")
public class Parcel {

    private int weight;
    private int width;
    private int height;
    private int length;

    public Parcel(int weight, int width, int height, int length) {
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public Parcel() {
    }

    @ApiModelProperty(name = "weight", dataType = "int", required = true)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @ApiModelProperty(name = "width", dataType = "int", required = true)
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @ApiModelProperty(name = "height", dataType = "int", required = true)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @ApiModelProperty(name = "height", dataType = "int", required = true)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parcel)) return false;
        Parcel parcel = (Parcel) o;
        return weight == parcel.weight && width == parcel.width && height == parcel.height &&
                length == parcel.length;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(weight, width, height, length);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).
                add("weight", weight).
                add("width", width).
                add("height", height).
                add("length", length).
                omitNullValues().
                toString();
    }

}
