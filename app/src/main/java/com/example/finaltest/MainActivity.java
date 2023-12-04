package com.example.finaltest;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    EditText edit;
    TextView textView;
    TextView text1;
    int page=1;
    static RequestQueue requestQueue;
    
    //이름 위도 경도 저장할 arraylist 선언
    ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();

    //맵 구현

    private GoogleMap mMap;
    MarkerOptions markerOptions;


    List<Marker> AllMarkers=new ArrayList<Marker>();

    String key1="eTTJA%2FVYi6MzBwBbXse%2B95TaJd%2FKIhTZxsf15GFWuwmC5sJJZFNttlsNKDllVFLb%2FVPAa784JM%2B2GfbFGXH%2FfQ%3D%3D"; //인코딩된 키
    String key2="eTTJA/VYi6MzBwBbXse+95TaJd/KIhTZxsf15GFWuwmC5sJJZFNttlsNKDllVFLb/VPAa784JM+2GfbFGXH/fQ=="; //디코딩된 키

    StringBuilder url=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=findViewById(R.id.edit);
        text1=findViewById(R.id.textview1);
        textView=findViewById(R.id.textview);


        if (requestQueue==null){
            requestQueue=Volley.newRequestQueue(getApplicationContext());
        }



    }

    public void onMapReady(final GoogleMap googleMap){
        mMap=googleMap;

        LatLng SEOUL =new LatLng(37.556,126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국수도");

        for(int i=0;i<list.size();i++){
            markerOptions.title(list.get(i).get(0));
            double lat=Double.parseDouble(list.get(i).get(1));
            double lng=Double.parseDouble(list.get(i).get(2));
            LatLng latLng=new LatLng(lat,lng);
            markerOptions.position(latLng);
            Marker mLocationMarker=mMap.addMarker(markerOptions);
            AllMarkers.add(mLocationMarker);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL,8));
    }
    public void sOnClick(View v){
        //입력받은 주소 인코딩 하기
        String str=edit.getText().toString();
        String location= null;
        try {
            location = URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        StringBuilder url=new StringBuilder();

        url.append("https://api.odcloud.kr/api/EvInfoServiceV2/v1/getEvSearchList?page=");
        url.append(page);
        url.append("&perPage=100&cond%5Baddr%3A%3ALIKE%5D=");
        if (location!=null){url.append(location);};
        url.append("&serviceKey=");
        url.append(key1);

        textView.setText(url);
        makeRequest();
        
    }


    public void makeRequest() {
        String url = textView.getText().toString();
        StringRequest request=new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        text1.setText(response);
                        parseData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text1.setText(error.getMessage());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params=new HashMap<String,String>();
                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }
    public void parseData(String response){
        Gson gson=new Gson();
        Dataset dataset=gson.fromJson(response,Dataset.class);
        System.out.println(dataset.matchCount);//총개수
        list.clear();
        removeAllMarkers();
        for(int i=0;i<dataset.data.size();i++){
            ChargeItem chargeItem=dataset.data.get(i);
            ArrayList<String> templist=new ArrayList<String>();
           templist.add(chargeItem.addr);
           templist.add(chargeItem.lat);
           templist.add(chargeItem.longi);
           list.add(templist);
        }

        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    public void removeAllMarkers(){
        for(Marker mLocationMarker:AllMarkers){
            mLocationMarker.remove();
        }
        AllMarkers.clear();
    }




}