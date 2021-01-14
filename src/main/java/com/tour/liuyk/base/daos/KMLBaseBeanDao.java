package com.tour.liuyk.base.daos;

import com.tour.liuyk.base.beans.KMLBaseBean;
import com.tour.liuyk.base.beans.TestBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KMLBaseBeanDao extends CrudRepository<KMLBaseBean, Long> {
    List<KMLBaseBean> findByName(String name);

    List<KMLBaseBean> findByStart(String start);

    List<KMLBaseBean> findByEnd(String end);

}
