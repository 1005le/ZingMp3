package com.example.admin.zingmp3.Fragment;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.zingmp3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayNhacaFragment extends Fragment {
  View view;

    public PlayNhacaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_nhaca, container, false);

        return view;
    }

}
