package com.hanwool.saleapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hanwool.saleapp.R;
import com.hanwool.saleapp.modal.Account;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanlytaikhoanAdminAdapter  extends RecyclerView.Adapter<QuanlytaikhoanAdminAdapter.ItemHolder> {
    Context context;
    ArrayList<Account> arrayAccount;
    ProgressBar progressBar;
    public QuanlytaikhoanAdminAdapter(Context context, ArrayList<Account> arrayAccount) {
        this.context = context;
        this.arrayAccount = arrayAccount;
    }

    @Override
    public QuanlytaikhoanAdminAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_quanlytaikhoanadmin,null);
        QuanlytaikhoanAdminAdapter.ItemHolder itemHolder= new QuanlytaikhoanAdminAdapter.ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(QuanlytaikhoanAdminAdapter.ItemHolder holder, int position) {
        Account account = arrayAccount.get(position);
        holder.txtId.setText( String.valueOf(account.getId()));
        holder.txtUsername.setText(account.getUserName());
        holder.txtEmailKH.setText(account.getEmail());


    }

    @Override
    public int getItemCount() {
        return arrayAccount.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder{

        public TextView txtId, txtUsername, txtEmailKH;


        public ItemHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtUsername= itemView.findViewById(R.id.txtUsername);
            txtEmailKH= itemView.findViewById(R.id.txtEmailKH);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, ChitietsanphamActivity.class);
//                    intent.putExtra("thongtinsanpham",arrayAccount.get(getPosition()));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                }
//            });

        }
    }
}
