package com.x.multicdn;

public class TrackInfo {
    public String url;
    public Integer totalByte;
    public Double totalTime;
    TrackInfo(String pUrl, Integer pTotalByte, Double pTotalTime){
        url = pUrl;
        totalByte = pTotalByte;
        totalTime = pTotalTime;
    }
}
