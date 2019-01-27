package com.example.admin.zingmp3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.R;

import java.util.ArrayList;

/**
 * Created by Admin on 6/10/2018.
 */

public class PlayBaiHatAdapter extends RecyclerView.Adapter<PlayBaiHatAdapter.ViewHolder>{
   Context context;
   ArrayList<BaiHat> arrBaiHat;

    public PlayBaiHatAdapter(Context context, ArrayList<BaiHat> arrBaiHat) {
        this.context = context;
        this.arrBaiHat = arrBaiHat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_play_baihat, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     BaiHat bh = arrBaiHat.get(position);
     holder.tvSTT.setText(position + 1 + "");
     holder.tvTenBaiHat.setText(arrBaiHat.get(position).getTenBH());
     holder.tvTenCaSi.setText(arrBaiHat.get(position).getCaSi());
    }

    @Override
    public int getItemCount() {
        return arrBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     TextView tvSTT, tvTenBaiHat, tvTenCaSi;
        public ViewHolder(View itemView) {
            super(itemView);
            tvSTT = itemView.findViewById(R.id.tvPlayNhacIndex);
            tvTenBaiHat = itemView.findViewById(R.id.tvPlayNhacTenBaiHat);
            tvTenCaSi = itemView.findViewById(R.id.tvPlayNhacTenCaSi);
        }
    }

}
