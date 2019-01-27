package com.example.admin.zingmp3.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.zingmp3.Activity.DanhSachBaiHatActivity;
import com.example.admin.zingmp3.Activity.DanhSachTatCaChuDeActivity;
import com.example.admin.zingmp3.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.admin.zingmp3.Model.ChuDe;
import com.example.admin.zingmp3.Model.TheLoai;
import com.example.admin.zingmp3.Model.TheLoaiTrongNgay;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChuDeVaTheLoaiFragment extends Fragment {
View view ;
HorizontalScrollView horizontalScrollView;
TextView tvXemThem;

    public ChuDeVaTheLoaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chu_de_va_the_loai, container, false);
       horizontalScrollView = view.findViewById(R.id.horizontalScollView);
       tvXemThem = view.findViewById(R.id.tvXemThem);

       tvXemThem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), DanhSachTatCaChuDeActivity.class);
               startActivity(intent);
           }
       });
       getData();
        return view;
    }

    private void getData() {
        DataSevice dataSevice = APIService.getService();
        Call<TheLoaiTrongNgay> callBack = dataSevice.getTheLoaiTrongNgay();
        callBack.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay = response.body();
               // Log.d("AAA",theLoaiTrongNgay.getTheLoai().get(0).getTenTheLoai());
                final ArrayList<ChuDe> arrayChuDe = new ArrayList<>();
                //add them 1 mang cung kieu voi mang chuDe
                arrayChuDe.addAll(theLoaiTrongNgay.getChuDe());

                final ArrayList<TheLoai> arrayTheLoai = new ArrayList<>();
                //add them 1 mang cung kieu voi mang chuDe
                arrayTheLoai.addAll(theLoaiTrongNgay.getTheLoai());

                //tao view de add
                LinearLayout linearLayout =new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580,250);
               layoutParams.setMargins(10,20,10,30);
                for(int i=0;i< arrayChuDe.size();i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(arrayChuDe.get(i).getHinhChuDe() !=null){
                        Picasso.with(getActivity()).load(arrayChuDe.get(i).getHinhChuDe()).into(imageView);
                    }
                 cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("chude",arrayChuDe.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int j=0;j< arrayChuDe.size();j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(arrayTheLoai.get(j).getHinhTheLoai() !=null){
                        Picasso.with(getActivity()).load(arrayTheLoai.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                     //kichh vao the loiaj chuyen du lieu cho man hinh activi danh sach bai hat
                    //goi bien vi tri tuong ung voi j thi khong duoc
                    //do do can khai bao bien finalJ

                       final int finalJ =j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai",arrayTheLoai.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }

}
