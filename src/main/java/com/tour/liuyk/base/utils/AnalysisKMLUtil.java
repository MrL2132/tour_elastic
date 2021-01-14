package com.tour.liuyk.base.utils;

import com.tour.liuyk.base.beans.CoordPointBean;
import com.tour.liuyk.base.beans.KMLBaseBean;
import com.tour.liuyk.base.beans.MarkPointBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AnalysisKMLUtil {

    public static List<String> getKmlFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile() && tempList[i].getName().endsWith("kml")) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
        }
        return files;
    }

    //KML文件信息转换Json
    public static KMLBaseBean getBeanFromKML(String path) {
        KMLBaseBean kmlBaseBean = new KMLBaseBean();
        ArrayList<CoordPointBean> coordPointBeans = new ArrayList<>();
        ArrayList<MarkPointBean> markPointBeans = new ArrayList<>();

        SAXReader reader = new SAXReader();

        File file = new File(path);


        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Element root = document.getRootElement();

        List<Element> childElements = root.elements();

        for (Element child : childElements) {

            List<Element> elements = child.elements();
            for (Element element : elements) {
                if (element.getName().toString().equals("name")) {

                    //获取kml文件输出名
                    kmlBaseBean.setName(element.getData().toString());

                    String reg = "[^\u4e00-\u9fa5a-zA-Z]";

                    try {
                        String region = element.getData().toString().replaceAll(reg, "");
//                        System.out.println("region" + region);

//                        kmlBaseBean.setStart(region.split("到")[0]);
//                        kmlBaseBean.setEnd(region.split("到")[1]);
                    } catch (Exception e) {
//                        System.out.println("名称不规则");

//                        kmlBaseBean.setStart("NULL");
//                        kmlBaseBean.setEnd("NULL");
                    }
                }

                if (element.getName().equals("Folder")) {
//                    System.out.println(element.attributeValue("id"));
                    List<Element> folder = element.elements();
                    for (Element elefloder : folder) {
//                        System.out.println("--"+elefloder.getName());

                        //标记点
                        if (elefloder.getName().equals("Placemark") && element.attributeValue("id").equals("TbuluHisPointFolder")) {
                            List<Element> eleplacemark = elefloder.elements();

                            MarkPointBean markPointBean = new MarkPointBean();
                            for (Element placemark : eleplacemark) {

                                if (placemark.getName().equals("name")) {
//                                    System.out.println(placemark.getData().toString());

                                    markPointBean.setPointName(placemark.getData().toString());
                                }
                                if (placemark.getName().equals("Point")) {
                                    List<Element> points = placemark.elements();
                                    for (Element point : points) {
//                                        System.out.println(point.getData());
                                        String[] coords = point.getData().toString().split(",");

//                                        coord.addProperty("height", coords[2]);

                                        markPointBean.setLatitude(coords[1]);
                                        markPointBean.setLongitude(coords[0]);
                                    }
                                }

                            }


                            markPointBeans.add(markPointBean);

                        }

                        kmlBaseBean.setMarkList(markPointBeans);


                        //路径
                        if (elefloder.getName().equals("Placemark") && element.attributeValue("id").equals("TbuluTrackFolder")) {
                            List<Element> eleplacemark = elefloder.elements();
                            for (Element placemark : eleplacemark) {

                                if (placemark.getName().equals("Track")) {
                                    List<Element> tracks = placemark.elements();

                                    CoordPointBean coordPointBean = new CoordPointBean();
                                    for (Element track : tracks) {
                                        if (track.getName().equals("coord")) {
//                                            System.out.println(track.getData().toString());
                                            String[] coords = track.getData().toString().split(" ");


                                            coordPointBean.setLatitude(coords[1]);
                                            coordPointBean.setLongitude(coords[0]);
                                            coordPointBean.setHeight(coords[2]);


//                                        System.out.println(track.getName());
                                        }

                                        coordPointBeans.add(coordPointBean);
                                    }
                                }

                            }

                            kmlBaseBean.setCoordList(coordPointBeans);
                        }
//                        System.out.println(kmlBaseBean);


                    }
                }

            }
        }

//        System.out.println(coordJson);

        Double startLat = Double.valueOf(coordPointBeans.get(0).getLatitude());
        Double startLon = Double.valueOf(coordPointBeans.get(0).getLongitude());
//        System.out.println("1:"+startLat+";2:"+startLon);

        kmlBaseBean.setStart(new GeoPoint(startLat, startLon));

        Double endLat = Double.valueOf(coordPointBeans.get(coordPointBeans.size() - 1).getLatitude());
        Double endLon = Double.valueOf(coordPointBeans.get(coordPointBeans.size() - 1).getLongitude());
//        System.out.println("1:"+endLat+";2:"+endLon);

        kmlBaseBean.setEnd(new GeoPoint(endLat, endLon));

//        System.out.println(kmlBaseBean);

        return kmlBaseBean;

    }


}
