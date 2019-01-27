package com.example.admin.zingmp3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DanhSachBaiHat implements Serializable{

@SerializedName("IdBH")
@Expose
private String idBH;
@SerializedName("tenBH")
@Expose
private String tenBH;
@SerializedName("hinhBH")
@Expose
private String hinhBH;
@SerializedName("caSi")
@Expose
private String caSi;
@SerializedName("linkBH")
@Expose
private String linkBH;
@SerializedName("luotThich")
@Expose
private String luotThich;

public String getIdBH() {
return idBH;
}

public void setIdBH(String idBH) {
this.idBH = idBH;
}

public String getTenBH() {
return tenBH;
}

public void setTenBH(String tenBH) {
this.tenBH = tenBH;
}

public String getHinhBH() {
return hinhBH;
}

public void setHinhBH(String hinhBH) {
this.hinhBH = hinhBH;
}

public String getCaSi() {
return caSi;
}

public void setCaSi(String caSi) {
this.caSi = caSi;
}

public String getLinkBH() {
return linkBH;
}

public void setLinkBH(String linkBH) {
this.linkBH = linkBH;
}

public String getLuotThich() {
return luotThich;
}

public void setLuotThich(String luotThich) {
this.luotThich = luotThich;
}

}