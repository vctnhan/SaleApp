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

import com.hanwool.saleapp.modal.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphammoiAdapter extends RecyclerView.Adapter<SanphammoiAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraySanpham;
    ProgressBar progressBar;
    public SanphammoiAdapter(Context context, ArrayList<Sanpham> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_spmoinhat,null);
        ItemHolder itemHolder= new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Sanpham sanpham = arraySanpham.get(position);
        holder.txtNewPhoneName.setText(sanpham.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtNewPhonePrice.setText("Giá: "+ decimalFormat.format(sanpham.getGiasp())+ "VNĐ");
        Picasso.with(context).load(sanpham.getHinhanhsp())
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.imgerror)
                        .into(holder.imgNewPhone);

    }

    @Override
    public int getItemCount() {
        return arraySanpham.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgNewPhone;
        public TextView txtNewPhoneName, txtNewPhonePrice;


        public ItemHolder(View itemView) {
            super(itemView);
            imgNewPhone = itemView.findViewById(R.id.imgNewPhone);
            txtNewPhonePrice= itemView.findViewById(R.id.txtNewPhonePrice);
            txtNewPhoneName= itemView.findViewById(R.id.txtNewPhoneName);

        }
    }
}
