package com.example.admin.zingmp3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.zingmp3.Model.Playlist;
import com.example.admin.zingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 26/9/2018.
 */

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView txtTemPlaylist;
        ImageView  imgPlaylist;
        ImageView imgBackground;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if( convertView == null){
            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView =inflater.inflate(R.layout.dong_playlist,null);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, parent, false);


            viewHolder.txtTemPlaylist = convertView.findViewById(R.id.tvTemPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imgIconPlaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imgBackGroudPlaylist);

            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.imgBackground);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgPlaylist);
        viewHolder.txtTemPlaylist.setText(playlist.getTen());

        return convertView;
    }
}
