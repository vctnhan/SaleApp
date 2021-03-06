package com.hanwool.saleapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hanwool.saleapp.adapter.LoaispAdapter;
import com.hanwool.saleapp.adapter.SanphammoiAdapter;
import com.hanwool.saleapp.modal.Loaisp;
import com.hanwool.saleapp.modal.Sanpham;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoaisanphamActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView lvPhoneBrand;
    ArrayList<Loaisp> mangLoaisp;
    Loaisp loaisp;
    LoaispAdapter loaispAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaisanpham_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AnhXa();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getLoaisp();
        loadDataSanpham();
    }


    public void AnhXa(){
        lvPhoneBrand= findViewById(R.id.lvPhoneBrand);
        mangLoaisp = new ArrayList<>();
        loaispAdapter = new LoaispAdapter(getApplicationContext(),mangLoaisp);
        lvPhoneBrand.setHasFixedSize(true);
        lvPhoneBrand.setLayoutManager
                (new GridLayoutManager(getApplicationContext(),2));
        lvPhoneBrand.setAdapter(loaispAdapter);

    }
    private void getLoaisp() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int ID = 0;
                    String Tenloaisp = "";
                    String Hinhanhloaisp= "";
                    for (int i =0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tenloaisp= jsonObject.getString("tenloaisp");
                            Hinhanhloaisp= jsonObject.getString("hinhanhloaisp");
                            mangLoaisp.add(new Loaisp(ID,Tenloaisp,Hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();

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
    public void GetDataSanpham(){

    }
    public void loadDataSanpham(){

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
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuGiohang:
                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
          Intent i = new Intent(LoaisanphamActivity.this, MainActivity.class);
          startActivity(i);
            finish();
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(LoaisanphamActivity.this, TatcasanphamActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_slideshow) {
            //loaisp
        } else if (id == R.id.nav_manage) {
            //gio hang
            Intent i = new Intent(this, GiohangActivity.class);
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
}
