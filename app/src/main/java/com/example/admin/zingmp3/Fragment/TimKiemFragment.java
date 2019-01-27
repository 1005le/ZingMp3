package com.example.admin.zingmp3.Fragment;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.admin.zingmp3.Adapter.SearchBaiHatAdapter;
import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimKiemFragment extends Fragment {

    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewsearchBaihat;
    TextView txtKhongcoDuLieu;
     SearchBaiHatAdapter searchBaiHatAdapter;

    public TimKiemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar = view.findViewById(R.id.toolbarSearchBH);
        recyclerViewsearchBaihat = view.findViewById(R.id.recyclerViewSearchBH);
        txtKhongcoDuLieu = view.findViewById(R.id.textViewkhongcoDuLieu);
        //khoi tao toolbar cho Fragment
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        //Tao menu de khi kich vao truyen du lieu , thuc hien phan search
        //gan chuc nang cho menu
        setHasOptionsMenu(true);
         return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.search_view,menu);
       //anh xa de biet la dang click vao cai nao trong item
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               //nhap xong va an enter
              //  Log.d("BBB",query);
                searchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               //khi co su thay doi
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void searchTuKhoaBaiHat(String tukhoa){
        DataSevice dataSevice = APIService.getService();
        Call<List<BaiHat>> callBack = dataSevice.getSearchBaiHat(tukhoa);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangBaiHat = (ArrayList<BaiHat>) response.body();
                if( mangBaiHat.size() >0){
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), mangBaiHat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearchBaihat.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchBaihat.setAdapter(searchBaiHatAdapter);
                    txtKhongcoDuLieu.setVisibility(View.GONE);
                    recyclerViewsearchBaihat.setVisibility(View.VISIBLE);

                }else{
                    recyclerViewsearchBaihat.setVisibility(View.GONE);
                    txtKhongcoDuLieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

}
