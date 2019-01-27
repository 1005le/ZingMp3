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

import com.example.admin.zingmp3.Activity.PlayNhacActivity;
import com.example.admin.zingmp3.Model.BaiHat;
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

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {
   Context context;
   ArrayList<BaiHat> baiHatArrayList;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }
    @NonNull
    @Override
    //dung de gan Layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihathot,parent,false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      BaiHat baiHat = baiHatArrayList.get(position);
      holder.txtCaSi.setText(baiHat.getCaSi());
      holder.txtTen.setText(baiHat.getTenBH());
        Picasso.with(context).load(baiHat.getHinhBH()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen, txtCaSi;
        ImageView imgHinh, imgLuotThich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.tvBaiHatHot);
            txtCaSi = itemView.findViewById(R.id.tvCaSiBaiHatHot);
            imgHinh = itemView.findViewById(R.id.imgViewBaiHatHot);
            imgLuotThich = itemView.findViewById(R.id.imgViewLuotThich);

            imgLuotThich.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           //  Toast.makeText(context,baiHatArrayList.get(getPosition()).getTenBH(), Toast.LENGTH_SHORT).show();
          imgLuotThich.setImageResource(R.drawable.iconloved);
             DataSevice dataSevice = APIService.getService();
           //  getPosition(): lay vi tri hien tai cua bai Hat co id la bao nhieu tren phia Server
            Call<String> callBack = dataSevice.updateLuotThich("1",baiHatArrayList.get(getPosition()).getIdBH());
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
            imgLuotThich.setEnabled(false);
         }
     });
     itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context, PlayNhacActivity.class);
             intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
             context.startActivity(intent);
         }
     });

        }
    }

}
