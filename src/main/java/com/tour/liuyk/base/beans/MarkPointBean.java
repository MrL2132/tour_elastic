package com.tour.liuyk.base.beans;

import lombok.Data;

/*
            "pointname":"前方路废了，左转向下有一条路",
            "latitude":"27.788038",
            "longitude":"100.330917"
 */
@Data
public class MarkPointBean {
    @Override
    public String toString() {
        return "MarkPointBean{" +
                "pointName='" + pointName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    String pointName;
    String latitude;
    String longitude;

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
