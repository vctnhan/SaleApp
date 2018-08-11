package com.hanwool.saleapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

public class SanPhamtrongLoaispAdapter extends RecyclerView.Adapter<SanPhamtrongLoaispAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraySanpham;
    ProgressBar progressBar;
    public SanPhamtrongLoaispAdapter(Context context, ArrayList<Sanpham> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;
    }

    @Override
    public SanPhamtrongLoaispAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_tatcasanpham,null);
        SanPhamtrongLoaispAdapter.ItemHolder itemHolder= new SanPhamtrongLoaispAdapter.ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(SanPhamtrongLoaispAdapter.ItemHolder holder, int position) {
        Sanpham sanpham = arraySanpham.get(position);
        holder.txtAllPhoneName.setText(sanpham.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtAllPhonePrice.setText("Giá: "+ decimalFormat.format(sanpham.getGiasp())+ "VNĐ");
        holder.txtAllPhoneDetail.setMaxLines(2);
        holder.txtAllPhoneDetail.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtAllPhoneDetail.setText(sanpham.getMotasp());
        Picasso.with(context).load(sanpham.getHinhanhsp())
                .placeholder(R.drawable.logo)
                .error(R.drawable.imgerror)
                .into(holder.imgAllPhone);

    }

    @Override
    public int getItemCount() {
        return arraySanpham.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgAllPhone;
        public TextView txtAllPhoneName, txtAllPhonePrice, txtAllPhoneDetail;


        public ItemHolder(View itemView) {
            super(itemView);
            imgAllPhone = itemView.findViewById(R.id.imgAllPhone);
            txtAllPhonePrice= itemView.findViewById(R.id.txtAllPhonePrice);
            txtAllPhoneName= itemView.findViewById(R.id.txtAllPhoneName);
            txtAllPhoneDetail= itemView.findViewById(R.id.txtAllPhoneDetail);

        }
    }
}
