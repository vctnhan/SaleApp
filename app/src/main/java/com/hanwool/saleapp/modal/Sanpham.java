package com.hanwool.saleapp.modal;
public class Sanpham {
    public  int id;
    public  String Tensp ;
    public Integer Giasp;
    public  String Hinhanhsp, Motasp;
    public int idlsp;

    public Sanpham(int id, String tensp, Integer giasp, String hinhanhsp, String motasp, int idlsp) {
        this.id = id;
        Tensp = tensp;
        Giasp = giasp;
        Hinhanhsp = hinhanhsp;
        Motasp = motasp;
        this.idlsp = idlsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public Integer getGiasp() {
        return Giasp;
    }

    public void setGiasp(Integer giasp) {
        Giasp = giasp;
    }

    public String getHinhanhsp() {
        return Hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        Hinhanhsp = hinhanhsp;
    }

    public String getMotasp() {
        return Motasp;
    }

    public void setMotasp(String motasp) {
        Motasp = motasp;
    }

    public int getidlsp() {
        return idlsp;
    }

    public void setidlsp(int idlsp) {
        this.idlsp = idlsp;
    }
}
