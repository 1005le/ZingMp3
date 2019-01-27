package com.example.admin.zingmp3.Service;

import com.example.admin.zingmp3.Model.Album;
import com.example.admin.zingmp3.Model.BaiHat;
import com.example.admin.zingmp3.Model.ChuDe;
import com.example.admin.zingmp3.Model.Playlist;
import com.example.admin.zingmp3.Model.TheLoai;
import com.example.admin.zingmp3.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Admin on 26/9/2018.
 */

public interface DataSevice {

    @GET("playlistforcurrent.php")
    Call<List<Playlist>> getPlaylistCurrent();

    @GET("chudevatheloai.php")
    Call<TheLoaiTrongNgay> getTheLoaiTrongNgay();

    @GET("albumhot.php")
    Call<List<Album>> getAlbumHot();
//lay du lieu ve, chua thuc hien tuong tac gi doi voi server nen
    //khong truyen tham so 
    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> getBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHattheoPlayList(@Field("idplaylist") String idplaylist);

    //chi doc va khong gui gi len thi dung GET//lay tu xem tat cac cac playlist
    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> getDanhSachCacPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>getDanhSachBaiHatTheoChuDe(@Field("idTheLoai") String idtheloai);

    @GET("tatcachude.php")
     Call<List<ChuDe>> getAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getTheLoaiTheoChuDe(@Field("idChuDe") String idchude);

     @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotThich") String luotThich, @Field("IdBH") String idBH);

      @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> getSearchBaiHat(@Field("tukhoa") String tukhoa);

}
