package com.example.admin.zingmp3.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.admin.zingmp3.Adapter.ViewPagerPlayNhacAdapter;
import com.example.admin.zingmp3.Fragment.DiaNhacFragment;
import com.example.admin.zingmp3.Fragment.PlayDanhSachBaiHatFragment;
import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {
  android.support.v7.widget.Toolbar toolbarPlayNhac;
  TextView tvTimeSong, tvTotalTimeSong;
  SeekBar seekBar;
  ImageView imgPlay, imgPre, imgRepeat, imgNext, imgRandom;
  ViewPager viewPagerPlayNhac;
  public static ArrayList<BaiHat>mangBaiHat = new ArrayList<BaiHat>();
      ViewPagerPlayNhacAdapter adapterNhac;
     PlayDanhSachBaiHatFragment playDanhSachBaiHatFragment;
     DiaNhacFragment diaNhacFragment;
  //play nhac
    MediaPlayer mediaPlayer;

  //  @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        //kiem tra mang
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
        getDataIntent();
        eventClick();
    }

    private void eventClick() {
        //khi co ca khuc phat len, thi nut play thay doi trang thais
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
             if(adapterNhac.getItem(1) !=null){
                 if(mangBaiHat.size() > 0){
                     diaNhacFragment.Playnhac(mangBaiHat.get(0).getHinhBH());
                    //1 so truong hop se xoa di hinh nah cu
                     handler.removeCallbacks(this);
                 }else{
                     handler.postDelayed(this,300);
                 }
             }
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
       mangBaiHat.clear();
        if (intent != null) {
            //truyen 1 ca khuc duy nhat
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangBaiHat.add(baiHat);
            }
            //chuyen mot mang bai hat
            if (intent.hasExtra("cacbaihat")) {
                //intent ca mang Bai Hat
                ArrayList<BaiHat> arrayListBH = intent.getParcelableArrayListExtra("cacbaihat");
                mangBaiHat = arrayListBH;
            }
        }
    }
    private void init() {
        toolbarPlayNhac = findViewById(R.id.toolbarPlayNhac);
        tvTimeSong = findViewById(R.id.tvTimeSong);
        tvTotalTimeSong = findViewById(R.id.textViewToTalTimeSong);
        seekBar = findViewById(R.id.seekBarSong);
        imgPlay = findViewById(R.id.imageButtonPlay);
        imgNext = findViewById(R.id.imageButtonNext);
        imgPre = findViewById(R.id.imageButtonPreview);
        imgRepeat = findViewById(R.id.imageButtonRepeat);
        imgRandom = findViewById(R.id.imageButtonSuffe);


        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        viewPagerPlayNhac = findViewById(R.id.viewPagerPlayNhac);

        adapterNhac = new ViewPagerPlayNhacAdapter(getSupportFragmentManager());
        //khoi tao va dua fragment vao ViewPagerManager
        diaNhacFragment = new DiaNhacFragment();
        playDanhSachBaiHatFragment = new PlayDanhSachBaiHatFragment();

        adapterNhac.addFragment(playDanhSachBaiHatFragment);
        adapterNhac.addFragment(diaNhacFragment);
        viewPagerPlayNhac.setAdapter(adapterNhac);
        //hinh anh bai hat duoc load len dia nhac
        diaNhacFragment = (DiaNhacFragment) adapterNhac.getItem(1);
      //khi ng dung click qua thi se play ca khuc dau tien luon
        if(mangBaiHat.size() > 0){
            //lay ca khuc dau tien
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenBH());
            new PlayMp3().execute(mangBaiHat.get(0).getLinkBH());
            imgPlay.setImageResource(R.drawable.iconpause);
            Log.d("play",mangBaiHat.get(0).getLinkBH());
        }

    }
    //tham so lan luot la : String : link cua bai hat , Void : tham so cap nhat len
    //String : tra ve link bai hat de thuc hien play ca khuc nay
  class PlayMp3 extends AsyncTask<String,Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            return strings[0];
        }
//NHan du lieu
        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            //play ca khuc duoi dang online
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                //khi dua ca khuc ma thoi gian cap nhat lau thi se nhay
                //vao ham nnay
                public void onCompletion(MediaPlayer mp) {
                    //tranh viec khi moi bat dau play ca khuc , chua load duoc
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

                //khoi tao du lieu ua ca khuc
                mediaPlayer.setDataSource(baihat);
                //media muon phat nhac
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            //ham cap nhat tong thoi gian cua ca khuc
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        //lay tong thoi gian cua ca khuc
        tvTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        //khi muon keo du lieu den phut thu 2 thi thoi gian se duoc cap nhta len seekBar
        //gia su bai hat chi keo dai 1p
        seekBar.setMax(mediaPlayer.getDuration());
    }


}
