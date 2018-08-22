package com.hanwool.saleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hanwool.saleapp.adapter.QuanlydonhangAdminAdapter;
import com.hanwool.saleapp.adapter.QuanlytaikhoanAdminAdapter;
import com.hanwool.saleapp.modal.Account;
import com.hanwool.saleapp.modal.Donhang;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Quanlytaikhoan_AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView lvAllAccount;
    ArrayList<Account> mangaccount;
    QuanlytaikhoanAdminAdapter quanlytaikhoanAdminAdapter;
    TextView ifNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlytaikhoan__admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Anhxa();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Anhxa() {
        lvAllAccount= findViewById(R.id.lvAllAccount);
        mangaccount= new ArrayList<>();
        quanlytaikhoanAdminAdapter = new QuanlytaikhoanAdminAdapter(getApplicationContext(),mangaccount);
        ifNoData = findViewById(R.id.ifNoData);
        lvAllAccount.setHasFixedSize(true);
        lvAllAccount.setLayoutManager
                (new LinearLayoutManager(this));
        lvAllAccount.setAdapter(quanlytaikhoanAdminAdapter);

        getTaikhoan();
    }

    private void getTaikhoan() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLayTaikhoanAdmin, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int ID = 0;
                    String TenKH = "";
                    String Username= "";
                    String Password= "";
                    String EmailKH= "";

                    for (int i =0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("user_id");
                            TenKH= jsonObject.getString("tenkhachhang");
                            Username= jsonObject.getString("user_name");
                            Password = jsonObject.getString("password");
                            EmailKH= jsonObject.getString("email");
                            mangaccount.add(new Account(ID,Username,TenKH,EmailKH,Password));
                            quanlytaikhoanAdminAdapter.notifyDataSetChanged();
                            if (mangaccount.size()<=0){
                                ifNoData.setVisibility(View.VISIBLE);
                                lvAllAccount.setVisibility(View.INVISIBLE);
                                quanlytaikhoanAdminAdapter.notifyDataSetChanged();
                            }else {
                                lvAllAccount.setVisibility(View.VISIBLE);
                                ifNoData.setVisibility(View.INVISIBLE);
                                quanlytaikhoanAdminAdapter.notifyDataSetChanged();
                                if (mangaccount.size()<=0) {
                                    ifNoData.setVisibility(View.VISIBLE);
                                    lvAllAccount.setVisibility(View.INVISIBLE);
                                    quanlytaikhoanAdminAdapter.notifyDataSetChanged();
                                }

                            }

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.quanlytaikhoan__admin, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Quản lý tài khoản
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this, Quanlydonhang_AdminActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
