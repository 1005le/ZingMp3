package com.example.admin.zingmp3.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.zingmp3.Adapter.DanhSachBaiHatAdapter;
import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.Model.DanhSachBaiHat;
import com.example.admin.zingmp3.Model.Playlist;
import com.example.admin.zingmp3.Model.TheLoai;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    Playlist playlist;
    ImageView imageViewDanhSachCaKhuc;
    ArrayList<BaiHat> mangBaiHat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    TheLoai theloai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        anhXa();

        //kiem tra mang
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        init();
        //Buoc 2: xu ly du lieu tuong tac voi Server
        if(playlist !=null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(), playlist.getHinhPlaylist());
            //gui du lieu len server
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if(theloai != null && !theloai.getTenTheLoai().equals("")){
            setValueInView(theloai.getTenTheLoai(), theloai.getHinhTheLoai());
            getDanhsachBaiHatTheoTheLoai(theloai.getIdTheLoai());
        }
    }

    private void getDanhsachBaiHatTheoTheLoai(final String idTheloai) {
        DataSevice dataSevice = APIService.getService();
        Call<List<BaiHat>> callBack = dataSevice.getDanhSachBaiHatTheoChuDe(idTheloai);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
               mangBaiHat = (ArrayList<BaiHat>) response.body();
              //  Log.d("mmm",arrBHTheoTheLoai.get(1).getTenBH());
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
              evenClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplayllist) {
        DataSevice dataSevice = APIService.getService();
        Call<List<BaiHat>> callBack = dataSevice.getDanhSachBaiHattheoPlayList(idplayllist);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
              mangBaiHat = (ArrayList<BaiHat>) response.body();

              danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangBaiHat);
             recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
             recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
               evenClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh){
    collapsingToolbarLayout.setTitle(ten);
        URL url = null;
        try {
            //url de gan du lieu len collapsingToolbar
            url = new URL(hinh);
        Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
       //sau khi lay duoc
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imageViewDanhSachCaKhuc);
    }

    private void init() {
        //chuc nang cua action Bar duoc thay the bang thanh toolBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        //su kien floating button
        floatingActionButton.setEnabled(false);
    }
//buoc 1: vao pthuc nay truoc de nhan du lieu cua man hinh gui ve
    private void DataIntent() {
        Intent intent = getIntent();
        if(intent!=null){
            if( intent.hasExtra("itemplaylist")){
                //nhan du lieu la 1 Object
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");

            }
            if(intent.hasExtra("idtheloai")){
             theloai = (TheLoai) intent.getSerializableExtra("idtheloai");

            }
        }
    }

    private void evenClick(){
     floatingActionButton.setEnabled(true);
     floatingActionButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
             intent.putExtra("cacbaihat",mangBaiHat);
             startActivity(intent);
         }
     });
    }


    private void anhXa() {
        coordinatorLayout = findViewById(R.id.coordinator);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbarDanhSach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerViewDanhSachBaiHat);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imageViewDanhSachCaKhuc = findViewById(R.id.imgViewDanhSachCaKhuc);
    }

}
