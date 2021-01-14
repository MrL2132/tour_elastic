package com.tour.liuyk.base.services;

import com.tour.liuyk.base.beans.KMLBaseBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TestGeoService extends ElasticsearchRepository<KMLBaseBean, String> {
}
