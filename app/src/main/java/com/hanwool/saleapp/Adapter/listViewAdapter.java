package com.hanwool.saleapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanwool.saleapp.MainActivity;
import com.hanwool.saleapp.R;
import com.hanwool.saleapp.dataListView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class listViewAdapter extends ArrayAdapter<dataListView> {
    private Context context;
    private int resource;
    private List<dataListView> arrContact;
    ImageView imgNewPhone;

    public listViewAdapter(Context context, int resource, ArrayList<dataListView> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lvtrangchu, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtPhoneName = (TextView) convertView.findViewById(R.id.txtPhoneName);
            viewHolder.txtPhonePrice = (TextView) convertView.findViewById(R.id.txtPhonePrice);
            viewHolder.imgNewPhone = (ImageView) convertView.findViewById(R.id.imgNewPhone);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        dataListView data = arrContact.get(position);
//        viewHolder.tvAvatar.setBackgroundColor(contact.getColor());
//        viewHolder.tvAvatar.setText(String.valueOf(position+1));

        viewHolder.txtPhoneName.setText(data.getName());
        viewHolder.txtPhonePrice.setText(data.getPrice());
      String url = data.getImage();
//        Glide.with(MainActivity.this)
//                .load(a)
//                .centerCrop()
//                .override(40,40)
//                .into(imgNewPhone);
        try {
            URL a = new URL(url.toString());
            Bitmap bmp = BitmapFactory.decodeStream(a.openConnection().getInputStream());
            imgNewPhone.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    public class ViewHolder {
        TextView txtPhoneName, txtPhonePrice;
        ImageView imgNewPhone;

    }
}