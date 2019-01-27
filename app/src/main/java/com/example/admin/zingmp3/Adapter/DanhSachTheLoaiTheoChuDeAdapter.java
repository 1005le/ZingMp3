package com.example.admin.zingmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.zingmp3.Activity.DanhSachBaiHatActivity;
import com.example.admin.zingmp3.Model.DanhSachBaiHat;
import com.example.admin.zingmp3.Model.TheLoai;
import com.example.admin.zingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 4/10/2018.
 */

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder>{
   Context context;
   ArrayList<TheLoai> arrTheLoai;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> arrTheLoai) {
        this.context = context;
        this.arrTheLoai = arrTheLoai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai_theo_chude, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        TheLoai theLoai = arrTheLoai.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgTheloai);
         holder.tvTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return arrTheLoai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTheloai;
        TextView tvTenTheLoai;
        public ViewHolder(View itemView) {
            super(itemView);
            imgTheloai = itemView.findViewById(R.id.imgTheLoaiTheoChuDe);
            tvTenTheLoai = itemView.findViewById(R.id.tvTenTheLoaiTheoChuDe);

     //Bat su kien cho ViewHolder
           imgTheloai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai",arrTheLoai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
