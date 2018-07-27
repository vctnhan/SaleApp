package com.hanwool.saleapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.hanwool.saleapp.R;

import com.hanwool.saleapp.modal.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class  ViewHolder{
        TextView txtTenloaisp;
        ImageView imgLoaisp;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.item_lvtrangchu, null);
//            viewHolder.txtTenloaisp = view.findViewById(R.id.txtLoaisp);
//            viewHolder.txtTenloaisp = view.findViewById(R.id.imgLoaisp);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Loaisp loaisp = (Loaisp) getItem(i);
        viewHolder.txtTenloaisp.setText(loaisp.getTenloaisp());
        Picasso.with(context)
                .load(loaisp.getHinhanhloaisp())
                .placeholder(ProgressBar.VISIBLE)
                .error(R.drawable.imgerror)
                .into(viewHolder.imgLoaisp);

        return view;
    }
}
