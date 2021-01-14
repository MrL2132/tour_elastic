package com.tour.liuyk.base.services;

import com.tour.liuyk.base.beans.KMLBaseBean;
import com.tour.liuyk.base.beans.TestBean;

import java.util.List;

public interface KMLBaseBeanService {
    Iterable<KMLBaseBean> findAll();

    void save(List<KMLBaseBean> list);

    void save(KMLBaseBean bean);

    List<KMLBaseBean> findByName(String name);

    List<KMLBaseBean> findByStart(String start);

    List<KMLBaseBean> findByEnd(String end);
}
