package com.hanwool.saleapp;

public class dataListView {
    String Image;
    String Name;
    String Price;


    public dataListView( String image, String name, String price) {
        Image = image;
        Name = name;
        Price = price;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }
}
