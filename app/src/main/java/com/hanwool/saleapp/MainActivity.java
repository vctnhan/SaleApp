package com.hanwool.saleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hanwool.saleapp.adapter.SanphammoiAdapter;
import com.hanwool.saleapp.modal.Account;
import com.hanwool.saleapp.modal.Giohang;
import com.hanwool.saleapp.modal.Loaisp;
import com.hanwool.saleapp.modal.Sanpham;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    HashMap<String, String> HashMapForURL ;
    HashMap<String, Integer> HashMapForLocalRes ;
    TextView txtTen;
    Account account;
    ProgressBar progressBar;
    SliderLayout slideAds;
    RecyclerView lvNewPhone;
    public static ArrayList<Giohang> manggiohang;
    ArrayList<Sanpham> mangsanpham;
    SanphammoiAdapter sanphammoiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AnhXa();
        Intent intent = getIntent();
        account = new Account();
        account = (Account) intent.getSerializableExtra("login");
        txtTen.setText(account.getUserName());
        progressBar= new ProgressBar(this);

        ProgressBar progressBar =new ProgressBar(this);
        SliderLayout slideAds = findViewById(R.id.slideAds);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference samsungFB = database.getReference("Samsung");

        // Slide ảnh

        //Call this method if you want to add images from URL .
        AddImagesUrlOnline();

        //Call this method to add images from local drawable folder .
        //AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for(String name : HashMapForURL.keySet()){

            TextSliderView textSliderView = new TextSliderView(MainActivity.this);

            textSliderView
                    .description (name)
                    .image(HashMapForURL.get( name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            slideAds.addSlider(textSliderView);
        }
        slideAds.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        slideAds.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        slideAds.setCustomAnimation(new DescriptionAnimation());

        slideAds.setDuration(4000);

        slideAds.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) MainActivity.this);
        //
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Glide.with(this /* context */)
//                .using(new FirebaseImageLoader())
//                .load(storageReference)
//                .into(imgAds);
        getDulieuspmoi();
    }
    public void AnhXa(){
        lvNewPhone= findViewById(R.id.lstNewPhone);
        mangsanpham = new ArrayList<>();
        sanphammoiAdapter = new SanphammoiAdapter(getApplicationContext(),mangsanpham);
        lvNewPhone.setHasFixedSize(true);
        lvNewPhone.setLayoutManager
                (new GridLayoutManager(getApplicationContext(),2));
        lvNewPhone.setAdapter(sanphammoiAdapter);
         txtTen = findViewById(R.id.txtTen);

        if (manggiohang != null) {

        } else {
            manggiohang = new ArrayList<>();
        }
    }
    private void getDulieuspmoi() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanSanpham, new Response.Listener<JSONArray>() {
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
                            sanphammoiAdapter.notifyDataSetChanged();

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
    protected void onStop() {

//        slideAds.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this, slider.getBundle().get("extra").toString()  , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImagesUrlOnline(){

        HashMapForURL = new HashMap<String, String>();

        HashMapForURL.put("PALM HEIGHTS", "http://www.zmientrung.com/wp-content/uploads/2018/03/banner-qua%CC%89ng-ca%CC%81o-ba%CC%82%CC%81t-%C4%91o%CC%A3%CC%82ng-sa%CC%89n-2-1.jpg");
        HashMapForURL.put("MARC JACOBS", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/321cbe21866251.56308c50540bc.jpg");
        HashMapForURL.put("VIENTHONGA", "https://cdn1.vienthonga.vn/image/2016/8/3/100000_khaitruong-bac-lieu-1140x285-1.jpg");

    }

//    public void AddImageUrlFormLocalRes(){
//
//        HashMapForLocalRes = new HashMap<String, Integer>();
//
//        HashMapForLocalRes.put("CupCake", R.drawable.cupcake);
//        HashMapForLocalRes.put("Donut", R.drawable.donut);
//        HashMapForLocalRes.put("Eclair", R.drawable.eclair);
//
//
//    }

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //trangchu
        } else if (id == R.id.nav_gallery) {
            //tat ca sp
           Intent i = new Intent(MainActivity.this, TatcasanphamActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_slideshow) {
            //loai sp
            Intent i = new Intent(MainActivity.this, LoaisanphamActivity.class);
            startActivity(i);
            finish();
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
