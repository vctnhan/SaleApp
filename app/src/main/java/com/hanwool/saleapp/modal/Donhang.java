package com.hanwool.saleapp.modal;

import java.io.Serializable;

public class Donhang implements Serializable {
    public  int iddh;
    public  String TenKH;
    public Integer SdtKH;
    public  String EmailKH;

    public Donhang(int iddh, String tenKH, Integer sdtKH, String emailKH) {
        this.iddh = iddh;
        TenKH = tenKH;
        SdtKH = sdtKH;
        EmailKH = emailKH;
    }

    public int getIddh() {
        return iddh;
    }

    public void setIddh(int iddh) {
        this.iddh = iddh;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public Integer getSdtKH() {
        return SdtKH;
    }

    public void setSdtKH(Integer sdtKH) {
        SdtKH = sdtKH;
    }

    public String getEmailKH() {
        return EmailKH;
    }

    public void setEmailKH(String emailKH) {
        EmailKH = emailKH;
    }
}
