package com.example.admin.zingmp3.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
//Parcelabe co the ,gui, truyen mang  duoi dang Object
public class BaiHat implements Parcelable{

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

    protected BaiHat(Parcel in) {
        idBH = in.readString();
        tenBH = in.readString();
        hinhBH = in.readString();
        caSi = in.readString();
        linkBH = in.readString();
        luotThich = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBH);
        dest.writeString(tenBH);
        dest.writeString(hinhBH);
        dest.writeString(caSi);
        dest.writeString(linkBH);
        dest.writeString(luotThich);
    }
}