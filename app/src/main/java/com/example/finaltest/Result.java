package com.example.finaltest;

public class Result {
    private String addr;//주소
    private String lat;//위도
    private String longi;//경도

    private int count;

    public Result(String addr,String lat, String longi){
        this.addr=addr;
        this.lat=lat;
        this.longi=longi;
    }

    public String getAddr(){
        return this.addr;
    }
    public String getLat(){
        return this.lat;
    }
    public String getLongi(){
        return this.longi;
    }

}
