package com.tour.liuyk.base.beans;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.ArrayList;

//通过这个注解，可以不用写gettersetter方法
@Data
//通过这个注解可以声明一个文档，指定其所在的索引库和type
@Document(indexName = "kmldoc", type = "kmlbean")
public class KMLBaseBean {

    public KMLBaseBean() {
    }

    public KMLBaseBean(long id, String name, GeoPoint start, GeoPoint end, ArrayList<CoordPointBean> coordList, ArrayList<MarkPointBean> markList) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.coordList = coordList;
        this.markList = markList;
    }

    @Override
    public String toString() {
        return "KMLBaseBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startLat=" + start.getLat()+", startLon="+start.getLon() +
                ", endLat=" + end.getLat()+", endLon="+end.getLon() +
//                ", end=" + end +
                ", coordList=" + coordList +
                ", markList=" + markList +
                '}';
    }

    public GeoPoint getStart() {
        return start;
    }

    public void setStart(GeoPoint start) {
        this.start = start;
    }

    public GeoPoint getEnd() {
        return end;
    }

    public void setEnd(GeoPoint end) {
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<CoordPointBean> getCoordList() {
        return coordList;
    }

    public void setCoordList(ArrayList<CoordPointBean> coordList) {
        this.coordList = coordList;
    }

    public ArrayList<MarkPointBean> getMarkList() {
        return markList;
    }

    public void setMarkList(ArrayList<MarkPointBean> markList) {
        this.markList = markList;
    }

    @Id
    private long id;
    // 这里配置了分词器，字段类型，可以不配置，默认也可
    @Field(type = FieldType.Text)
    private String name;
    @GeoPointField
    private GeoPoint start;
    @GeoPointField
    private GeoPoint end;
    @Field(type = FieldType.Object)
    private ArrayList<CoordPointBean> coordList;
    @Field(type = FieldType.Object)
    private ArrayList<MarkPointBean> markList;
}
