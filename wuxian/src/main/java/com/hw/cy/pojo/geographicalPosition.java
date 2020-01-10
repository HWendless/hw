package com.hw.cy.pojo;

import java.util.HashMap;
import java.util.Map;

public class geographicalPosition {
    //国家名称
    private String countryname;
    //省名称
    private String provincename;
    //市名称
    private String cityname;
    //区域名称
    private String districtname;
    //街道名称
    private String streetname;
    //国家坐标
    private String countrylng;
    private String countrylat;
    private String countrylnglat;
    //省坐标
    private String provincelng;
    private String provincelat;
    private String provincelnglat;
    //市坐标
    private String citylng;
    private String citylat;
    private String citylnglat;
    //区域坐标
    private String districtlng;
    private String districtlat;
    private String districtlnglat;
    //街道坐标
    private String streetlng;
    private String streetlat;
    private String streetlnglat;

    private String level;

    public String getssqj()
    {
//        return this.provincename+this.cityname+this.districtname+this.getStreetname();
        return this.provincename+this.cityname+this.districtname;
    }

    public Map<String,String> getlnglat()
    {
        Map<String,String> map=new HashMap<String,String>();
        if(this.cityname!=null)
        {
            map.put("lng",this.citylng);
            map.put("lat",this.citylat);
            return map;
        }
        else
        {
            map.put("lng",this.provincelng);
            map.put("lat",this.provincelat);
            return map;
        }
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getCountrylng() {
        return countrylng;
    }

    public void setCountrylng(String countrylng) {
        this.countrylng = countrylng;
    }

    public String getCountrylat() {
        return countrylat;
    }

    public void setCountrylat(String countrylat) {
        this.countrylat = countrylat;
    }

    public String getCountrylnglat() {
        return countrylnglat;
    }

    public void setCountrylnglat(String countrylnglat) {
        this.countrylnglat = countrylnglat;
    }

    public String getProvincelng() {
        return provincelng;
    }

    public void setProvincelng(String provincelng) {
        this.provincelng = provincelng;
    }

    public String getProvincelat() {
        return provincelat;
    }

    public void setProvincelat(String provincelat) {
        this.provincelat = provincelat;
    }

    public String getProvincelnglat() {
        return provincelnglat;
    }

    public void setProvincelnglat(String provincelnglat) {
        this.provincelnglat = provincelnglat;
    }

    public String getCitylng() {
        return citylng;
    }

    public void setCitylng(String citylng) {
        this.citylng = citylng;
    }

    public String getCitylat() {
        return citylat;
    }

    public void setCitylat(String citylat) {
        this.citylat = citylat;
    }

    public String getCitylnglat() {
        return citylnglat;
    }

    public void setCitylnglat(String citylnglat) {
        this.citylnglat = citylnglat;
    }

    public String getDistrictlng() {
        return districtlng;
    }

    public void setDistrictlng(String districtlng) {
        this.districtlng = districtlng;
    }

    public String getDistrictlat() {
        return districtlat;
    }

    public void setDistrictlat(String districtlat) {
        this.districtlat = districtlat;
    }

    public String getDistrictlnglat() {
        return districtlnglat;
    }

    public void setDistrictlnglat(String districtlnglat) {
        this.districtlnglat = districtlnglat;
    }

    public String getStreetlng() {
        return streetlng;
    }

    public void setStreetlng(String streetlng) {
        this.streetlng = streetlng;
    }

    public String getStreetlat() {
        return streetlat;
    }

    public void setStreetlat(String streetlat) {
        this.streetlat = streetlat;
    }

    public String getStreetlnglat() {
        return streetlnglat;
    }

    public void setStreetlnglat(String streetlnglat) {
        this.streetlnglat = streetlnglat;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "geographicalPosition{" +
                "countryname='" + countryname + '\'' +
                ", provincename='" + provincename + '\'' +
                ", cityname='" + cityname + '\'' +
                ", districtname='" + districtname + '\'' +
                ", streetname='" + streetname + '\'' +
                ", countrylng='" + countrylng + '\'' +
                ", countrylat='" + countrylat + '\'' +
                ", countrylnglat='" + countrylnglat + '\'' +
                ", provincelng='" + provincelng + '\'' +
                ", provincelat='" + provincelat + '\'' +
                ", provincelnglat='" + provincelnglat + '\'' +
                ", citylng='" + citylng + '\'' +
                ", citylat='" + citylat + '\'' +
                ", citylnglat='" + citylnglat + '\'' +
                ", districtlng='" + districtlng + '\'' +
                ", districtlat='" + districtlat + '\'' +
                ", districtlnglat='" + districtlnglat + '\'' +
                ", streetlng='" + streetlng + '\'' +
                ", streetlat='" + streetlat + '\'' +
                ", streetlnglat='" + streetlnglat + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}