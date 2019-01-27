package com.example.admin.zingmp3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.example.admin.zingmp3.Adapter.DanhSachTatCaChuDeAdapter;
import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.Model.ChuDe;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaChuDeActivity extends AppCompatActivity {
  android.support.v7.widget.Toolbar toolbar;
  RecyclerView recyclerViewTatCacCacChuDe;
  DanhSachTatCaChuDeAdapter danhSachTatCaChuDeAdapter;
  ArrayList<ChuDe> arrChuDe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_chu_de);
        anhXa();
        getDataAllChuDe();
    }

    private void getDataAllChuDe() {
        DataSevice dataSevice = APIService.getService();
       Call<List<ChuDe>> callBack = dataSevice.getAllChuDe();
       callBack.enqueue(new Callback<List<ChuDe>>() {
           @Override
           public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
              arrChuDe = (ArrayList<ChuDe>) response.body();
               Log.d("chude",arrChuDe.get(1).getTenChuDe());
               danhSachTatCaChuDeAdapter = new DanhSachTatCaChuDeAdapter(DanhSachTatCaChuDeActivity.this
                       ,arrChuDe);
               recyclerViewTatCacCacChuDe.setLayoutManager(new GridLayoutManager(DanhSachTatCaChuDeActivity.this,1));
           recyclerViewTatCacCacChuDe.setAdapter(danhSachTatCaChuDeAdapter);
           }

           @Override
           public void onFailure(Call<List<ChuDe>> call, Throwable t) {

           }
       });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolBarTatCaCacChuDe);
        recyclerViewTatCacCacChuDe = findViewById(R.id.recycleViewTatCaCacChuDe);
        //
        setSupportActionBar(toolbar);
        //nut de back ve activity truoc
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tat Ca Cac Chu De");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
