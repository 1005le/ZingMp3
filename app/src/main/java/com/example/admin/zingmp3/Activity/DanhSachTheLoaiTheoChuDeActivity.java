package com.example.admin.zingmp3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.admin.zingmp3.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import com.example.admin.zingmp3.Model.ChuDe;
import com.example.admin.zingmp3.Model.TheLoai;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {
  ChuDe chuDe;
  RecyclerView recyclerViewTheLoaiTheoChuDe;
 android.support.v7.widget.Toolbar toolBarTheLoaiTheoChuDe;
 DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;
 ArrayList<TheLoai> arrTheLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        GetIntent();
        anhXa();
        getDataTheLoaiTheoChuDe(chuDe.getIdChuDe());
    }

    private void getDataTheLoaiTheoChuDe(String idchude) {
        DataSevice dataSevice = APIService.getService();
        Call<List<TheLoai>> callBack = dataSevice.getTheLoaiTheoChuDe(idchude);
        callBack.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                arrTheLoai = (ArrayList<TheLoai>) response.body();
              //  Log.d("theloai", arrayTheLoai.get(1).getTenTheLoai());
              //khoi tao Adapter
                danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this,arrTheLoai);
               recyclerViewTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this , 2));
                recyclerViewTheLoaiTheoChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void anhXa() {
        toolBarTheLoaiTheoChuDe = findViewById(R.id.toolBarTheLoiaTheoChuDe);
        recyclerViewTheLoaiTheoChuDe = findViewById(R.id.recyclerViewTheLoaiTheoChuDe);
        setSupportActionBar(toolBarTheLoaiTheoChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolBarTheLoaiTheoChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetIntent() {
        Intent intent = getIntent();
        if( intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this,chuDe.getTenChuDe(), Toast.LENGTH_SHORT).show();
        }
    }

}
