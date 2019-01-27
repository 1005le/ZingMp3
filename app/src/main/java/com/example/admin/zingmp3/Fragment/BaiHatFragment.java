package com.example.admin.zingmp3.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.zingmp3.Adapter.BaiHatHotAdapter;
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
public class BaiHatFragment extends Fragment {
  View view;
  RecyclerView recyclerViewBaiHatHot;
  BaiHatHotAdapter baiHatHotAdapter;
    public BaiHatFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_bai_hat, container, false);

       recyclerViewBaiHatHot = view.findViewById(R.id.recycleViewBaiHatHot);
        getData();
        return view;
    }

    private void getData() {
        DataSevice dataSevice = APIService.getService();
        Call<List<BaiHat>> callBack = dataSevice.getBaiHatHot();
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arrBaiHatHot = (ArrayList<BaiHat>) response.body();
               // Log.d("BH", arrBaiHatHot.get(0).getTenBH());
               baiHatHotAdapter = new BaiHatHotAdapter(getActivity(),arrBaiHatHot);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHatHot.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

}
