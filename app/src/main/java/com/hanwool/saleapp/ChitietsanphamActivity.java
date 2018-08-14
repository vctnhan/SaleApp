package com.hanwool.saleapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hanwool.saleapp.modal.Giohang;
import com.hanwool.saleapp.modal.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChitietsanphamActivity extends AppCompatActivity {

    TextView txtTen, txtGia, txtMota;
    ImageView imgChitiet;
    Spinner spinner;
    Button btnDatmua;
    int id = 0;
    String TenChiTiet = "";
    int GiaChiTiet = 0;
    String HinhAnhChiTiet = "";
    String MotaChiTiet = "";
    int IdSanPham = 0;
    Sanpham sanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Anhxa();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
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
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btnDatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() >0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;
                    for (int i = 0; i< MainActivity.manggiohang.size(); i++){
                        if (MainActivity.manggiohang.get(i).getIdsp() == id) {
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluongsp() >= 10) {
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChiTiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exist = true;

                        }
                    }
                    if (exist == false) {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long GiaMoi = soluong * GiaChiTiet;
                        MainActivity.manggiohang.add(new Giohang(id,TenChiTiet,GiaMoi,HinhAnhChiTiet,soluong));
                    }

                } else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long GiaMoi = soluong * GiaChiTiet;
                    MainActivity.manggiohang.add(new Giohang(id,TenChiTiet,GiaMoi,HinhAnhChiTiet,soluong));
                }
//                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
//                startActivity(intent);;
                Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
                Toast.makeText(getApplicationContext(), "Bạn đã thêm "+ spinner.getSelectedItem()+"sản phẩm "+ sanpham.getTensp() +" vào giỏ hàng" , Toast.LENGTH_SHORT ).show();
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        TenChiTiet = sanpham.getTensp();
        GiaChiTiet = sanpham.getGiasp();
        HinhAnhChiTiet = sanpham.getHinhanhsp();
        MotaChiTiet = sanpham.getMotasp();
        IdSanPham = sanpham.getidlsp();
        txtTen.setText(TenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá: " + decimalFormat.format(GiaChiTiet) + " VNĐ");
        txtMota.setText(MotaChiTiet);
        Picasso.with(getApplicationContext()).load(HinhAnhChiTiet)
                .placeholder(R.drawable.imgerror)
                .error(R.drawable.imgerror)
                .into(imgChitiet);


    }

    private void Anhxa() {
        txtTen = findViewById(R.id.txtTenchitietsanpham);
        txtGia = findViewById(R.id.txtGiachitietsanpham);
        txtMota = findViewById(R.id.txtMotachitietsanpham);
        imgChitiet = findViewById(R.id.imgChitietsanpham);
        spinner = findViewById(R.id.spinner);
        btnDatmua = findViewById(R.id.btnDatmua);
    }
}
