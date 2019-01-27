package com.example.admin.zingmp3.Service;

/**
 * Created by Admin on 26/9/2018.
 */

public class APIService {
  //    private static String base_url="https://linknghe.000webhostapp.com/server/";
    private static String base_url="http://192.168.1.3:1337/ngheNhac/";
  //  private static String base_url="http://192.168.43.123:1337/ngheNhac/";
    public static DataSevice getService(){
        //khoi tao cac phuong thuc tu DataService gui len
        return APIRetrofitCilent.getClient(base_url).create(DataSevice.class);

    }

}
