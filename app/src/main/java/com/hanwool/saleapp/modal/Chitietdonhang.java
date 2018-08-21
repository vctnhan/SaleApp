package com.hanwool.saleapp.modal;

import java.io.Serializable;

public class Chitietdonhang implements Serializable {
    int Id;
    int Madonhang;
    int Masanpham;
    String Tensanpham;
    Integer Giasanpham;
    Integer Soluongsanpham;

    public Chitietdonhang(int id, int madonhang, int masanpham, String tensanpham, Integer giasanpham, Integer soluongsanpham) {
        Id = id;
        Madonhang = madonhang;
        Masanpham = masanpham;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Soluongsanpham = soluongsanpham;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMadonhang() {
        return Madonhang;
    }

    public void setMadonhang(int madonhang) {
        Madonhang = madonhang;
    }

    public int getMasanpham() {
        return Masanpham;
    }

    public void setMasanpham(int masanpham) {
        Masanpham = masanpham;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public Integer getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        Giasanpham = giasanpham;
    }

    public Integer getSoluongsanpham() {
        return Soluongsanpham;
    }

    public void setSoluongsanpham(Integer soluongsanpham) {
        Soluongsanpham = soluongsanpham;
    }
}
