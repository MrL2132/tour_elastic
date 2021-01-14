package com.tour.liuyk.base.controllers;

import com.tour.liuyk.base.beans.KMLBaseBean;
import com.tour.liuyk.base.beans.TestBean;
import com.tour.liuyk.base.services.KMLBaseBeanService;
import com.tour.liuyk.base.services.TestGeoService;
import com.tour.liuyk.base.services.TestService;
import com.tour.liuyk.base.services.impl.TestGeoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: pyfysf
 * <p>
 * @qq: 337081267
 * <p>
 * @CSDN: http://blog.csdn.net/pyfysf
 * <p>
 * @blog: http://wintp.top
 * <p>
 * @email: pyfysf@163.com
 * <p>
 */
@RestController
@RequestMapping("/testes")
public class TestController {

    @Autowired
    TestGeoServiceImpl testGeoService;

    @Autowired
    KMLBaseBeanService kmlBaseBeanService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping("findAll")
    public Iterable<KMLBaseBean> findAll() {

        return kmlBaseBeanService.findAll();
    }

    //?latitude=27.683&longitude=100.763212&distance=100
    @RequestMapping("findGeo")
    public Iterable<KMLBaseBean> findGeo(double latitude, double longitude, String distance) {

        return testGeoService.findPage( latitude, longitude, distance);
    }

    @RequestMapping("list")
    public String save() {
        List<KMLBaseBean> list = null;
        kmlBaseBeanService.save(list);

        return "success";
    }

    @RequestMapping("save")
    public void save(KMLBaseBean bean) {
        kmlBaseBeanService.save(bean);
    }

    @RequestMapping("findByName")
    public List<KMLBaseBean> findByName(String name) {
        return kmlBaseBeanService.findByName(name);
    }

    @RequestMapping("findByStart")
    public List<KMLBaseBean> findByStart(String text) {
        return kmlBaseBeanService.findByStart(text);
    }

    @RequestMapping("findByEnd")
    public List<KMLBaseBean> findByEnd(String text) {
        return kmlBaseBeanService.findByEnd(text);
    }

}


