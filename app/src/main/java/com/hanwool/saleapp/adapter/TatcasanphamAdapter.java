package com.hanwool.saleapp.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hanwool.saleapp.ChitietsanphamActivity;
import com.hanwool.saleapp.R;

import com.hanwool.saleapp.interfaceToCompare.SoSanhGiaComporator;
import com.hanwool.saleapp.modal.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TatcasanphamAdapter extends RecyclerView.Adapter<TatcasanphamAdapter.ItemHolder> implements Filterable {
    Context context;
    ArrayList<Sanpham> arraySanpham;
    ArrayList<Sanpham> arrayResult;
    ProgressBar progressBar;


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
    public Filter getFilter() {
        return new Filter() {



            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Sanpham> results = new ArrayList<Sanpham>();
                if (arrayResult == null)
                    arrayResult = arraySanpham;
                if (constraint != null) {
                    if (arrayResult != null && arrayResult.size() > 0) {
                        for (final Sanpham sanpham : arrayResult) {
                            if (sanpham.getTensp().toLowerCase().contains(constraint.toString()))

                                results.add(sanpham);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                arraySanpham = (ArrayList<Sanpham>) results.values;
                notifyDataSetChanged();
            }
        };
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
