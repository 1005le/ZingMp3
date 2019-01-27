package com.example.admin.zingmp3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.zingmp3.Model.Playlist;
import com.example.admin.zingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 1/10/2018.
 */

public class DanhSachCacPlaylistAdapter extends RecyclerView.Adapter<DanhSachCacPlaylistAdapter.ViewHolder>{
   Context context;
   ArrayList<Playlist> arrayPlaylist;

    public DanhSachCacPlaylistAdapter(Context context, ArrayList<Playlist> arrayPlaylist) {
        this.context = context;
        this.arrayPlaylist = arrayPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_cacplaylist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Playlist playlist = arrayPlaylist.get(position);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imgHinhNen);
        holder.txtTenDanhSachCacPlaylist.setText(playlist.getTen());

    }

    @Override
    public int getItemCount() {
        return arrayPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhNen;
        TextView txtTenDanhSachCacPlaylist;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhNen =  itemView.findViewById(R.id.imagViewDanhSachCacPlaylist);
            txtTenDanhSachCacPlaylist = itemView.findViewById(R.id.textViewTenDanhSachCacPlaylist);

        }
    }
}
