package com.example.admin.zingmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.zingmp3.Activity.DanhSachTatCaChuDeActivity;
import com.example.admin.zingmp3.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.admin.zingmp3.Model.ChuDe;
import com.example.admin.zingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 4/10/2018.
 */

public class DanhSachTatCaChuDeAdapter extends RecyclerView.Adapter<DanhSachTatCaChuDeAdapter.ViewHolder>{
   Context context;
   ArrayList<ChuDe> arrChuDe;

    public DanhSachTatCaChuDeAdapter(Context context, ArrayList<ChuDe> arrChuDe) {
        this.context = context;
        this.arrChuDe = arrChuDe;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_chude,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      ChuDe chuDe = arrChuDe.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imageViewChuDe);
    }

    @Override
    public int getItemCount() {
        return arrChuDe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewChuDe;
        public ViewHolder(View itemView) {
            super(itemView);
            imageViewChuDe = itemView.findViewById(R.id.imgdongCacChuDe);
            imageViewChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("chude",arrChuDe.get(getPosition()));
                       context.startActivity(intent);
                }
            });
        }
    }
}
