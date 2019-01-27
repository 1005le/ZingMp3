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
 * Created by Admin on 18/10/2018.
 */

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>{
   Context context;
   ArrayList<BaiHat> mangBaiHat;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         BaiHat  baiHat = mangBaiHat.get(position);
         holder.txtTenBaiHat.setText(baiHat.getTenBH());
         holder.txtCaSi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhBH()).into(holder.imgViewBaiHat);

    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
               TextView txtTenBaiHat, txtCaSi;
               ImageView imgViewBaiHat, imgLuotThich;
               public ViewHolder(View itemView) {
                   super(itemView);
                   txtTenBaiHat = itemView.findViewById(R.id.tvSearchTenBaiHat);
                   txtCaSi = itemView.findViewById(R.id.tvSearchTenCaSi);
                   imgViewBaiHat = itemView.findViewById(R.id.imagViewSearchBaiHat);
                   imgLuotThich = itemView.findViewById(R.id.imgViewSearchLuotThich);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PlayNhacActivity.class);
                        intent.putExtra("cakhuc",mangBaiHat.get(getPosition()));
                        context.startActivity(intent);
                    }
                });
                imgLuotThich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgLuotThich.setImageResource(R.drawable.iconloved);
                        //Tao retrofit de ket noi voi server
                        DataSevice dataSevice = APIService.getService();
                        Call<String> callBack = dataSevice.updateLuotThich("1",mangBaiHat.get(getPosition()).getIdBH());
                        callBack.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if( ketqua.equals("success")){
                                    Toast.makeText(context, "Da Thich", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Loi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        imgLuotThich.setEnabled(false);
                    }
                });
               }
           }

}
