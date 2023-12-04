package com.example.finaltest;

public class ChargeItem {

    String addr;//주소
    String chargeTp;//타입 1:완속 2:급속
    String cpId;//충전기 ID
    String cpNm;//충전기 명칭
    String cpStat;//충전기상태코드 0:상태확인불가, 1:충전가능, 2:충전중, 3:고장/점검,  4:통신장애, 9:충전예약
    String cpTp;//충전방식 1:B타입(5핀),2:C타입(5핀),3:BC타입(5핀),4:BC타입(7핀),5:DC차데모,6:AC3상,7:DC콤보,8:DC차데모+DC콤보,9:DC차데모+AC3상,10:DC차데모+DC콤보+AC3상
    String csId;//충전소ID
    String csNm;//충전소 명칭
    String lat;//위도
    String longi;//경도
    String statUpdatetime;//충전기 상태 갱신시각

//    public String getAddr(){return addr;}
//    public String getCargeTp(){return chargeTp;}
//    public String getCpId() {return cpId;}
//    public String getCpNm(){return cpNm;}
//    public String getCpStat() {return cpStat;}
//    public String getCpTp() {return cpTp;}
//    public String getCsId() {return csId;}
//    public String getCsNm(){return csNm;}
//    public String getLat() {return lat;}
//    public String getLongi(){return longi;}
//    public String getStatUpdatetime(){return statUpdatetime;}
}

