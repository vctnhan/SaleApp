package com.hanwool.saleapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hanwool.saleapp.R;
import com.hanwool.saleapp.modal.Donhang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanlydonhangAdminAdapter extends RecyclerView.Adapter<QuanlydonhangAdminAdapter.ItemHolder> {
    Context context;
    ArrayList<Donhang> arrayDonhang;
    ProgressBar progressBar;
    public QuanlydonhangAdminAdapter(Context context, ArrayList<Donhang> arrayDonhang) {
        this.context = context;
        this.arrayDonhang = arrayDonhang;
    }

    @Override
    public QuanlydonhangAdminAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_quanlydonhangadmin,null);
        QuanlydonhangAdminAdapter.ItemHolder itemHolder= new QuanlydonhangAdminAdapter.ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(QuanlydonhangAdminAdapter.ItemHolder holder, int position) {
        Donhang donhang = arrayDonhang.get(position);
       holder.txtId.setText(String.valueOf(donhang.getIddh()));
        holder.txtTenKH.setText(donhang.getTenKH());
        DecimalFormat decimalFormat= new DecimalFormat("#########");
        holder.txtSdtKH.setText(decimalFormat.format(donhang.getSdtKH()));
        holder.txtEmailKH.setText(donhang.getEmailKH());


    }

    @Override
    public int getItemCount() {
        return arrayDonhang.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder{

        public TextView txtId, txtTenKH, txtSdtKH, txtEmailKH;


        public ItemHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtTenKH= itemView.findViewById(R.id.txtTenKH);
            txtSdtKH= itemView.findViewById(R.id.txtSdtKH);
            txtEmailKH= itemView.findViewById(R.id.txtEmailKH);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, ChitietsanphamActivity.class);
//                    intent.putExtra("thongtinsanpham",arrayDonhang.get(getPosition()));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                }
//            });

        }
    }
}
