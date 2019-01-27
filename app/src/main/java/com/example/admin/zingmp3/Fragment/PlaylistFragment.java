package com.example.admin.zingmp3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.zingmp3.Activity.DanhSachBaiHatActivity;
import com.example.admin.zingmp3.Activity.DanhSachCacPlaylistActivity;
import com.example.admin.zingmp3.Adapter.PlaylistAdapter;
import com.example.admin.zingmp3.Model.DanhSachBaiHat;
import com.example.admin.zingmp3.Model.Playlist;
import com.example.admin.zingmp3.R;
import com.example.admin.zingmp3.Service.APIService;
import com.example.admin.zingmp3.Service.DataSevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {
     View view;
     ListView lvPlaylist;
     TextView txtTitlePlaylist, txtViewXemThemPlaylist;
     //goi den Adapter
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> mangPlaylist;

    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_playlist, container, false);
       lvPlaylist = view.findViewById(R.id.lvPlaylist);
       txtTitlePlaylist = view.findViewById(R.id.tvtitlePlaylist);
       txtViewXemThemPlaylist = view.findViewById(R.id.tvViewMorePlaylist);
        getData();

        txtViewXemThemPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachCacPlaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void getData() {

        //cau hinh lai retrofit , gui len server de tuong tac du lieu
        DataSevice dataSevice = APIService.getService();

        Call<List<Playlist>> callBack = dataSevice.getPlaylistCurrent();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {

                 mangPlaylist = (ArrayList<Playlist>) response.body();
                Log.d("DD", mangPlaylist.get(0).getTen());
                Log.d("AB", mangPlaylist.get(1).getTen());
            playlistAdapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,mangPlaylist);
             lvPlaylist.setAdapter(playlistAdapter);

             setListViewHeightBasedOnChildren(lvPlaylist);

             lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                     //gui sang vi tri cua mang trong playlist
                     intent.putExtra("itemplaylist",mangPlaylist.get(position));
                     startActivity(intent);
                 }
             });
          }
            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    //class custom lai gioa dien ListView, giup cho ListView chi control trong
    //pham vi kich thuoc nhat dinh va k control ra ben ngoai
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
