package com.hanwool.saleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hanwool.saleapp.adapter.ChitietdonhangAdapter;
import com.hanwool.saleapp.adapter.QuanlydonhangAdminAdapter;
import com.hanwool.saleapp.adapter.SanphammoiAdapter;
import com.hanwool.saleapp.modal.Chitietdonhang;
import com.hanwool.saleapp.modal.Donhang;
import com.hanwool.saleapp.modal.Giohang;
import com.hanwool.saleapp.modal.Sanpham;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Chitietdonhang_AdminActivity extends AppCompatActivity {
    RecyclerView lvOrderDetails;
    Donhang donhang;
    ArrayList<Chitietdonhang> mangChitietdonhang;
    int a;
    ChitietdonhangAdapter chitietdonhangAdapter;


    Chitietdonhang chitietdonhang;
    String iddh;

//    ChitietdonhangAdapter chitietdonhangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietdonhang__admin);
        Intent i = getIntent();
        donhang = (Donhang) i.getSerializableExtra("madonhang");
         iddh = String.valueOf(donhang.getIddh());
        a= Integer.parseInt(iddh);
        mangChitietdonhang= new ArrayList<>();
        Anhxa();
        getDonhang();






    }

    private void Anhxa() {

    lvOrderDetails = findViewById(R.id.lvOrderDetails);

        chitietdonhangAdapter = new ChitietdonhangAdapter(getApplicationContext(),mangChitietdonhang);
        //
        lvOrderDetails.setHasFixedSize(true);
        lvOrderDetails.setLayoutManager
                (new LinearLayoutManager(this));
        lvOrderDetails.setAdapter(chitietdonhangAdapter);
    }
    private void getDonhang() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLayChitietdonhang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int ID = 0;
                    int Madonhang=0;
                    int Masanpham= 0;
                    String Tensanpham = "";
                    Integer Giasanpham=0;
                    Integer Soluongsanpham=0;
//                   for (int i =0; i<response.length(); i++) {
                        try {

                            JSONObject jsonObject = response.getJSONObject(a-1);
                            ID = jsonObject.getInt("id");
                            Madonhang = jsonObject.getInt("madonhang");
                            Masanpham = jsonObject.getInt("masanpham");
                            Tensanpham = jsonObject.getString("tensanpham");
                            Giasanpham = jsonObject.getInt("giasanpham");
                            Soluongsanpham = jsonObject.getInt("soluongsanpham");
                            mangChitietdonhang.add(new Chitietdonhang(ID, Madonhang, Masanpham, Tensanpham, Giasanpham, Soluongsanpham));


                                                        chitietdonhangAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                   }


//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}
