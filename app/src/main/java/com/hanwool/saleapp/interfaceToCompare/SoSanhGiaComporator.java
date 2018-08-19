package com.hanwool.saleapp.interfaceToCompare;

import com.hanwool.saleapp.modal.Sanpham;

import java.util.Comparator;

public class SoSanhGiaComporator implements Comparator<Sanpham> {


    @Override
    public int compare(Sanpham s1, Sanpham s2) {
        if (s1.Giasp == s2.Giasp)
            return 0;
        else if (s1.Giasp > s2.Giasp)
            return 1;
        else
            return -1;
    }

    }


