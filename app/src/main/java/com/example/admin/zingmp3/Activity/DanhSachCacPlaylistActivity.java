package com.example.admin.zingmp3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.example.admin.zingmp3.Adapter.DanhSachCacPlaylistAdapter;
import com.example.admin.zingmp3.Model.Playlist;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachCacPlaylistActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerViewDanhsachcacPlaylist;

    DanhSachCacPlaylistAdapter danhSachCacPlaylistAdapter;
    ArrayList<Playlist> arrayPlaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_cac_playlist);
        anhXa();
        init();
        getData();
    }

    private void getData() {
        DataSevice dataSevice = APIService.getService();
        Call<List<Playlist>> callBack = dataSevice.getDanhSachCacPlaylist();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                //Log.d("abc", arrayPlaylist.get(0).getTen());
               danhSachCacPlaylistAdapter = new DanhSachCacPlaylistAdapter(DanhSachCacPlaylistActivity.this,arrayPlaylist);
               recyclerViewDanhsachcacPlaylist.setLayoutManager(new GridLayoutManager(DanhSachCacPlaylistActivity.this, 2));

               recyclerViewDanhsachcacPlaylist.setAdapter(danhSachCacPlaylistAdapter);
            }
            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbarDanhsachcacplaylist);
        recyclerViewDanhsachcacPlaylist = findViewById(R.id.recyclerViewDanhsachCacPlaylist);

    }
}
