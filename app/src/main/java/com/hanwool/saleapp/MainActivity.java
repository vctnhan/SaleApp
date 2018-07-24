package com.hanwool.saleapp;

import android.content.Intent;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
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

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hanwool.saleapp.Adapter.listViewAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    HashMap<String, String> HashMapForURL ;
    HashMap<String, Integer> HashMapForLocalRes ;
    ProgressBar progressBar;
    SliderLayout slideAds;
    ListView lvNewPhone;
    TextView txtPhoneName, txtPhonePrice;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    ImageView imgNewPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar= new ProgressBar(this);
        imgNewPhone= findViewById(R.id.imgNewPhone);
        lvNewPhone= findViewById(R.id.lstNewPhone);
        arrayList  = new ArrayList<String>();

       arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lvNewPhone.setAdapter(arrayAdapter);

        Intent i = getIntent();
        ProgressBar progressBar =new ProgressBar(this);
       SliderLayout slideAds = findViewById(R.id.slideAds);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference samsungFB = database.getReference("Samsung");
        // lây thông tin đt Samsung từ firebase
        samsungFB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String a = dataSnapshot.getValue().toString();
//                Toast.makeText(MainActivity.this, a , Toast.LENGTH_LONG).show();
                arrayList.add(a);
//                arrayList.add(new dataListView(
//                        a,a,a
//                ));
//
//                listViewAdapter adapter = new listViewAdapter
//                        (MainActivity.this,R.layout.item_lvtrangchu,arrayList);
//
//
//
//
                    arrayAdapter.notifyDataSetChanged();
//                lvNewPhone.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //
        //firebase storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference newPhoneFB = storage.getReference("Samsung");
        //lay anh tu firebase
        StorageReference s9 = storageRef.child("Samsung/s9.jpg");
        StorageReference s8 = storageRef.child("Samsung/s8.png");
        StorageReference s7 = storageRef.child("Samsung/s7.png");
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
                    .description("Samsung Galaxy " + name)
                    .image(HashMapForURL.get( name))
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            slideAds.addSlider(textSliderView);
        }
        slideAds.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        slideAds.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        slideAds.setCustomAnimation(new DescriptionAnimation());

        slideAds.setDuration(3000);

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

    }
    public void getDataFB(){

    }
    @Override
    protected void onStop() {

        slideAds.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,"Samsung Galaxy "+ slider.getBundle().get("extra")  , Toast.LENGTH_SHORT).show();
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

        HashMapForURL.put("s7", "https://firebasestorage.googleapis.com/v0/b/sale-app-bae86.appspot.com/o/Samsung%2Fs7.png?alt=media&token=7fce03f1-08ae-48f5-b4e5-432f0adbe10c");
        HashMapForURL.put("s8", "https://firebasestorage.googleapis.com/v0/b/sale-app-bae86.appspot.com/o/Samsung%2Fs8.png?alt=media&token=033aa224-c484-4dd8-9fd0-22adc3603b73");
        HashMapForURL.put("s9", "https://firebasestorage.googleapis.com/v0/b/sale-app-bae86.appspot.com/o/Samsung%2Fs9.jpg?alt=media&token=d6b93be0-8911-42c3-9434-6cef934a1df5");

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
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
