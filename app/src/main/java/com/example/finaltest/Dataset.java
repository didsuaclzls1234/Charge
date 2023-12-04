package com.example.finaltest;

import java.util.ArrayList;

public class Dataset {
    int currentCount; //현재 페이지의 개수
    ArrayList<ChargeItem> data =new ArrayList<ChargeItem>();
    int matchCount; //검색 결과를 만족한 개수
    int page; //현재 페이지 번호
    int perPage; //페이지당 개수-최대 500개인것으로 보임
    int totalCount; //총 개수
}
