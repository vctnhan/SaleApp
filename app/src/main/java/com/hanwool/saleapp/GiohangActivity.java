package com.hanwool.saleapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hanwool.saleapp.adapter.GiohangAdapter;
import com.hanwool.saleapp.modal.Giohang;
import com.hanwool.saleapp.ultil.CheckConnection;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ListView lvGiohang;
    TextView txtThongbao;
    static TextView txtTongtien;
    Button btnThanhtoan, btnTieptucmua;
    GiohangAdapter giohangAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Anhxa();
        CheckData();
        EventUltil();
        CatchOnItemListView();
        EventButton();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void EventButton() {
        btnTieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//                finish();
                onBackPressed();
            }
        });
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(),ThongtinkhachhangActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm đẻ thanh toán");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.manggiohang.size() <= 0) {
                            txtThongbao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EventUltil();
                            if (MainActivity.manggiohang.size() <= 0) {
                                txtThongbao.setVisibility(View.VISIBLE);
                            } else {
                                txtThongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long Tongtien = 0;
        for(int i = 0;i<MainActivity.manggiohang.size();i++){
            Tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongtien.setText(decimalFormat.format(Tongtien) + "VNĐ");
    }

    private void CheckData() {
        if (MainActivity.manggiohang.size() <=0) {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        } else {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }

    private void Anhxa() {
        lvGiohang = findViewById(R.id.lvGiohang);
        txtThongbao = findViewById(R.id.txtThongbao);
        txtTongtien = findViewById(R.id.txtTongtien);
        btnThanhtoan = findViewById(R.id.btnThanhtoan);
        btnTieptucmua = findViewById(R.id.btnTieptucmua);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this,MainActivity.manggiohang);
        lvGiohang.setAdapter(giohangAdapter);

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
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // trang chu
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_gallery) {
            //tat ca sp
            Intent i = new Intent(this, TatcasanphamActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_slideshow) {
            //Loai sp
            Intent i = new Intent(this, LoaisanphamActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_manage) {
            //gio hang
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
