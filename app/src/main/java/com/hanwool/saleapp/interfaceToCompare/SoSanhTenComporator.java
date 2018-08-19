package com.hanwool.saleapp.interfaceToCompare;

import com.hanwool.saleapp.modal.Sanpham;

import java.util.Comparator;

public class SoSanhTenComporator implements Comparator<Sanpham> {
    @Override
    public int compare(Sanpham s1, Sanpham s2) {
        return s1.Tensp.compareTo(s2.Tensp);
    }
}
