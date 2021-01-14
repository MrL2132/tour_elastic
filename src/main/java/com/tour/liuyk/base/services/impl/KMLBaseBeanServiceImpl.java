package com.tour.liuyk.base.services.impl;

import com.tour.liuyk.base.beans.KMLBaseBean;
import com.tour.liuyk.base.daos.KMLBaseBeanDao;
import com.tour.liuyk.base.services.KMLBaseBeanService;
import com.tour.liuyk.base.utils.AnalysisKMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KMLBaseBeanServiceImpl implements KMLBaseBeanService {
    @Autowired
    KMLBaseBeanDao kmlBaseBeanDao;

    @Override
    public Iterable<KMLBaseBean> findAll() {
        return this.kmlBaseBeanDao.findAll();
    }

    @Override
    public void save(List<KMLBaseBean> list) {
        list = new ArrayList<>();

        List<String> kmlFiles = AnalysisKMLUtil.getKmlFiles("/Users/liuyk/Downloads");

        long count = kmlBaseBeanDao.count();

        for (String kmlFile : kmlFiles) {

            KMLBaseBean beanFromKML = AnalysisKMLUtil.getBeanFromKML(kmlFile);

            beanFromKML.setId(count++);

            list.add(beanFromKML);

        }

        kmlBaseBeanDao.saveAll(list);

    }

    @Override
    public void save(KMLBaseBean bean) {
        kmlBaseBeanDao.save(bean);
    }

    @Override
    public List<KMLBaseBean> findByName(String name) {
        return kmlBaseBeanDao.findByName(name);
    }

    @Override
    public List<KMLBaseBean> findByStart(String start) {
        return kmlBaseBeanDao.findByStart(start);
    }

    @Override
    public List<KMLBaseBean> findByEnd(String end) {
        return kmlBaseBeanDao.findByEnd(end);
    }
}
