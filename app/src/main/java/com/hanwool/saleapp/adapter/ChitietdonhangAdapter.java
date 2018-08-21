package com.hanwool.saleapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hanwool.saleapp.Chitietdonhang_AdminActivity;
import com.hanwool.saleapp.R;
import com.hanwool.saleapp.modal.Chitietdonhang;
import com.hanwool.saleapp.modal.Donhang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChitietdonhangAdapter  extends RecyclerView.Adapter<ChitietdonhangAdapter.ItemHolder> {
    Context context;
    ArrayList<Chitietdonhang> arrayChitietdonhang;

    public ChitietdonhangAdapter(Context context, ArrayList<Chitietdonhang> arrayChitietdonhang) {
        this.context = context;
        this.arrayChitietdonhang = arrayChitietdonhang;
    }

    @Override
    public ChitietdonhangAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_chitietdonhang,null);
        ChitietdonhangAdapter.ItemHolder itemHolder= new ChitietdonhangAdapter.ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ChitietdonhangAdapter.ItemHolder holder, int position) {
        Chitietdonhang chitietdonhang = arrayChitietdonhang.get(position);
        holder.txtMadonhang.setText(String.valueOf(chitietdonhang.getMadonhang()));
        holder.txtMasanpham.setText(String.valueOf(chitietdonhang.getMasanpham()));
        holder.txtTensanpham.setText(chitietdonhang.getTensanpham());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtGiasanpham.setText(decimalFormat.format(chitietdonhang.getGiasanpham()));
        holder.txtGiasanpham.setText(decimalFormat.format(chitietdonhang.getSoluongsanpham()));



    }

    @Override
    public int getItemCount() {
        return arrayChitietdonhang.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder{

        public TextView txtMadonhang, txtMasanpham, txtTensanpham, txtGiasanpham,txtSoluongsanpham;


        public ItemHolder(View itemView) {
            super(itemView);
            txtMadonhang = itemView.findViewById(R.id.txtMadonhang);
            txtMasanpham= itemView.findViewById(R.id.txtMasanpham);
            txtTensanpham= itemView.findViewById(R.id.txtTensanpham);
            txtGiasanpham= itemView.findViewById(R.id.txtGiasanpham);
            txtSoluongsanpham= itemView.findViewById(R.id.txtSoluongsanpham);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(context, Chitietdonhang_AdminActivity.class);
//                    intent.putExtra("madonhang",arrayChitietdonhang.get(getPosition()));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                }
//            });

        }
    }
}
