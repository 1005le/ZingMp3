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
import android.widget.Toast;

import com.example.admin.zingmp3.Activity.DanhSachBaiHatActivity;
import com.example.admin.zingmp3.Activity.PlayNhacActivity;
import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.Model.DanhSachBaiHat;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 30/9/2018.
 */

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
   ArrayList<BaiHat> arrDSBaiHat;
   Context context;

    public DanhSachBaiHatAdapter( Context context , ArrayList<BaiHat> arrDSBaiHat) {
        this.arrDSBaiHat = arrDSBaiHat;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_baihat, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat danhSachBaiHat = arrDSBaiHat.get(position);
        holder.tvSTT.setText(position+1+"");
        holder.tvTenBH.setText(arrDSBaiHat.get(position).getTenBH());
        holder.tvTenCaSiDS.setText(arrDSBaiHat.get(position).getCaSi());
        //Picasso.with(context).load(arrDSBaiHat.get(position).getHinhBH()).into(holder.imgViewLuotThich);

    }

    @Override
    public int getItemCount() {
        return arrDSBaiHat.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
           TextView tvSTT, tvTenBH, tvTenCaSiDS;
           ImageView imgViewLuotThich;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSTT = itemView.findViewById(R.id.tvSTT);
            tvTenBH = itemView.findViewById(R.id.tvTenBaiHatDanhSach);
            tvTenCaSiDS = itemView.findViewById(R.id.tvTenCaSiDanhSach);
            imgViewLuotThich = itemView.findViewById(R.id.imgluotThichDanhSach);

           imgViewLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(context,baiHatArrayList.get(getPosition()).getTenBH(), Toast.LENGTH_SHORT).show();
                   imgViewLuotThich.setImageResource(R.drawable.iconloved);
                    DataSevice dataSevice = APIService.getService();
                    //  getPosition(): lay vi tri hien tai cua bai Hat co id la bao nhieu tren phia Server
                    Call<String> callBack = dataSevice.updateLuotThich("1",arrDSBaiHat.get(getPosition()).getIdBH());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Sucess")){
                                Toast.makeText(context, "Da Thich", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Bi Loi", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                  imgViewLuotThich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",arrDSBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }



}
