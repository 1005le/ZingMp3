package com.example.admin.zingmp3.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.zingmp3.Activity.PlayNhacActivity;
import com.example.admin.zingmp3.Adapter.PlayBaiHatAdapter;
import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayDanhSachBaiHatFragment extends Fragment {
    View view;

    RecyclerView recyclerViewPlayBaiHat;
    PlayBaiHatAdapter playBaiHatAdapter;
    public PlayDanhSachBaiHatFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat, container, false);
       recyclerViewPlayBaiHat = view.findViewById(R.id.recyclerViewPlayBaiHat);
    if(PlayNhacActivity.mangBaiHat.size() >0) {
        playBaiHatAdapter = new PlayBaiHatAdapter(getActivity(), PlayNhacActivity.mangBaiHat);
        recyclerViewPlayBaiHat.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewPlayBaiHat.setAdapter(playBaiHatAdapter);

    }
        return view;
    }


}
