package com.hanwool.saleapp.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hanwool.saleapp.ChitietsanphamActivity;
import com.hanwool.saleapp.R;

import com.hanwool.saleapp.modal.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TatcasanphamAdapter extends RecyclerView.Adapter<TatcasanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraySanpham;
    ProgressBar progressBar;
    private List<Sanpham> listSanpham = null;

    public TatcasanphamAdapter(Context context, ArrayList<Sanpham> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_tatcasanpham,null);
        ItemHolder itemHolder= new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChitietsanphamActivity.class);
                    intent.putExtra("thongtinsanpham",arraySanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        }
    }

}
