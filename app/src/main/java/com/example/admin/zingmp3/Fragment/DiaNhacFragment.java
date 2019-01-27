package com.example.admin.zingmp3.Fragment;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.admin.zingmp3.R;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.animation.ValueAnimator.RESTART;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaNhacFragment extends Fragment {

    View view;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    public DiaNhacFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);
      circleImageView = view.findViewById(R.id.imgViewCirclce);
      objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360);
      objectAnimator.setDuration(10000);
      objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());

        return view;
    }

    public void Playnhac(String hinhanh) {
        //  Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgPlaylist);
        Picasso.with(getContext()).load(hinhanh).into(circleImageView);
    }

}

