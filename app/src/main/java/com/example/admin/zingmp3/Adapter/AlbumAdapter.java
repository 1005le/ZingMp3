package com.example.admin.zingmp3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.zingmp3.Model.Album;
import com.example.admin.zingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 28/9/2018.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arrayAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrayAlbum) {
        this.context = context;
        this.arrayAlbum = arrayAlbum;
    }
    public AlbumAdapter(ArrayList<Album> arrAlbum) {
        this.arrayAlbum = arrAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          Album album = arrayAlbum.get(position);
          holder.tvCaSiAlbum.setText(album.getTenCaSiAlbum());
           holder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imageViewAlbum);
    }

    @Override
    public int getItemCount() {
        //mang co bao nhieu dong thi no ve len bay nhieu
        return arrayAlbum.size();
    }

    //recycleView phai nam trong class
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewAlbum;
        TextView txtTenAlbum, tvCaSiAlbum;
        public ViewHolder(View itemView) {
            super(itemView);
            imageViewAlbum = itemView.findViewById(R.id.imgAlbum);
            txtTenAlbum = itemView.findViewById(R.id.tvTenAlbum);
            tvCaSiAlbum = itemView.findViewById(R.id.tvTenCaSiAlbum);

        }
    }

}
