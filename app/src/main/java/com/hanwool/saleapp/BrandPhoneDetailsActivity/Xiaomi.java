package com.hanwool.saleapp.BrandPhoneDetailsActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hanwool.saleapp.R;
import com.hanwool.saleapp.adapter.SanPhamtrongLoaispAdapter;
import com.hanwool.saleapp.modal.Sanpham;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Xiaomi extends AppCompatActivity {
    RecyclerView lvPhoneInBrand;
    int ID = 0;
    String Tensp = "";
    Integer Giasp=0;
    String Hinhanhsp= "";
    String Motasp="";
    int idlsp = 0;
    SanPhamtrongLoaispAdapter sanPhamtrongLoaispAdapter;
    ArrayList<Sanpham> mangsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaomi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent i = getIntent();
        AnhXa();
    }

    private void AnhXa() {
        lvPhoneInBrand= findViewById(R.id.lvPhoneInBrand);
        mangsanpham= new ArrayList<>();
        sanPhamtrongLoaispAdapter = new SanPhamtrongLoaispAdapter(getApplicationContext(),mangsanpham);
        //
        lvPhoneInBrand.setHasFixedSize(true);
        lvPhoneInBrand.setLayoutManager
                (new LinearLayoutManager(this));
        lvPhoneInBrand.setAdapter(sanPhamtrongLoaispAdapter);
        getInformation();
    }

    private void getInformation() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanXiaomi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int ID = 0;
                    String Tensp = "";
                    Integer Giasp=0;
                    String Hinhanhsp= "";
                    String Motasp="";
                    int idlsp = 0;
                    for (int i =0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tensp= jsonObject.getString("tensanpham");
                            Giasp= jsonObject.getInt("giasanpham");
                            Hinhanhsp= jsonObject.getString("hinhanhsanpham");
                            Motasp= jsonObject.getString("motasanpham");
                            idlsp= jsonObject.getInt("idloaisanpham");
                            mangsanpham.add(new Sanpham(ID,Tensp,Giasp,Hinhanhsp,Motasp,idlsp));
                            sanPhamtrongLoaispAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}
