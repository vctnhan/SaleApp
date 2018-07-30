package com.hanwool.saleapp.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hanwool.saleapp.R;

import com.hanwool.saleapp.modal.Loaisp;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LoaispAdapter extends RecyclerView.Adapter<LoaispAdapter.ItemHolder> {
    Context context;
    ArrayList<Loaisp> arrayLoaisp;
    ProgressBar progressBar;
    public LoaispAdapter(Context context, ArrayList<Loaisp> arrayLoaisp) {
        this.context = context;
        this.arrayLoaisp = arrayLoaisp;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_loaisp,null);
        ItemHolder itemHolder= new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Loaisp loaisp = arrayLoaisp.get(position);
        holder.txtBrandName.setText(loaisp.getTenloaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.logo)
                .error(R.drawable.imgerror)
                .into(holder.imgBrandPhone);

    }

    @Override
    public int getItemCount() {
        return arrayLoaisp.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgBrandPhone;
        public TextView txtBrandName;


        public ItemHolder(View itemView) {
            super(itemView);
            imgBrandPhone = itemView.findViewById(R.id.imgPhoneBrand);
            txtBrandName= itemView.findViewById(R.id.txtPhoneBrand);


        }
    }
}
