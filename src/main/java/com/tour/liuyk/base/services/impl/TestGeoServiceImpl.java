package com.tour.liuyk.base.services.impl;

import com.tour.liuyk.base.beans.KMLBaseBean;
import com.tour.liuyk.base.services.TestGeoService;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestGeoServiceImpl  {

    @Resource
    private TestGeoService testGeoService;

    public Iterable<KMLBaseBean> findPage(double latitude, double longitude, String distance) {
        // 间接实现了QueryBuilder接口
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        // 以某点为中心，搜索指定范围
        GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("start");
        distanceQueryBuilder.point(latitude, longitude);
        // 定义查询单位：公里
        distanceQueryBuilder.distance(distance, DistanceUnit.KILOMETERS);
        boolQueryBuilder.filter(distanceQueryBuilder);

        return testGeoService.search(boolQueryBuilder);
    }
}
