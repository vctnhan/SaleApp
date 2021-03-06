package com.hanwool.saleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hanwool.saleapp.adapter.TatcasanphamAdapter;
import com.hanwool.saleapp.interfaceToCompare.SoSanhGiaComporator;
import com.hanwool.saleapp.interfaceToCompare.SoSanhTenComporator;
import com.hanwool.saleapp.modal.Account;
import com.hanwool.saleapp.modal.Sanpham;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class TatcasanphamActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    SearchView searchView;
RecyclerView lvAllPhone;
TatcasanphamAdapter tatcasanphamAdapter;
ArrayList<Sanpham> mangsanpham;
    ArrayList<Account> mangAccount;
Button btnAsc;
private boolean ascending = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tatcasanpham);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Anhxa();
       getAllPhone();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void Anhxa() {
        lvAllPhone= findViewById(R.id.lvAllPhone);
        mangsanpham= new ArrayList<>();
        tatcasanphamAdapter = new TatcasanphamAdapter(getApplicationContext(),mangsanpham);
        btnAsc= findViewById(R.id.btnAsc);
        lvAllPhone.setHasFixedSize(true);
        lvAllPhone.setLayoutManager
                (new LinearLayoutManager(this));
        lvAllPhone.setAdapter(tatcasanphamAdapter);



       

    }
    private void getAllPhone() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanTatcasanpham, new Response.Listener<JSONArray>() {
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
                            tatcasanphamAdapter.notifyDataSetChanged();


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



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_main, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        searchView = (SearchView) itemSearch.getActionView();
        //set OnQueryTextListener cho search view để thực hiện search theo text

        searchView.setQueryHint("Tìm kiếm...");
        searchView.setOnQueryTextListener(this);
        return true;
    }
    public void sortByAsc(View view) {

       processSortGia(ascending);
        ascending = !ascending;
    }
    public void sortByAlpha(View view) {
        processSortTen(ascending);
        ascending = !ascending;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
//        switch (item.getItemId()) {
//            case R.id.menuGiohang:
//                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
//                startActivity(intent);
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if (TextUtils.isEmpty(s)){
            tatcasanphamAdapter.getFilter().filter("");
//            onRestart();


        }else {
            tatcasanphamAdapter.getFilter().filter(s.toString());


        }
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // trang chu
            Intent i = new Intent(TatcasanphamActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_gallery) {
            //tatcasp
        } else if (id == R.id.nav_slideshow) {
            //loai sp
            Intent i = new Intent(TatcasanphamActivity.this, LoaisanphamActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_manage) {
            //gio hang
            Intent i = new Intent(TatcasanphamActivity.this, GiohangActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.log_out) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    private void processSortGia(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
        {
            Collections.sort(mangsanpham, new SoSanhGiaComporator());
        }
        else
        {
            Collections.reverse(mangsanpham);
        }
        tatcasanphamAdapter = new TatcasanphamAdapter(getApplicationContext(),mangsanpham);
      lvAllPhone.setAdapter(tatcasanphamAdapter);
//        tatcasanphamAdapter.notifyDataSetChanged();

    }


    private void processSortTen(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
        {
            Collections.sort(mangsanpham,new SoSanhTenComporator());

        }
        else
        {
            Collections.reverse(mangsanpham);
        }
        tatcasanphamAdapter = new TatcasanphamAdapter(getApplicationContext(),mangsanpham);
        lvAllPhone.setAdapter(tatcasanphamAdapter);
//        tatcasanphamAdapter.notifyDataSetChanged();

    }
}
