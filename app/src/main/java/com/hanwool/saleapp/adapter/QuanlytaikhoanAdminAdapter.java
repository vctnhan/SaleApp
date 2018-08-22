package com.hanwool.saleapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hanwool.saleapp.GiohangActivity;
import com.hanwool.saleapp.MainActivity;
import com.hanwool.saleapp.Quanlytaikhoan_AdminActivity;
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
    public void onBindViewHolder(QuanlytaikhoanAdminAdapter.ItemHolder holder, final int position) {
        Account account = arrayAccount.get(position);
        holder.txtId.setText( String.valueOf(account.getId()));
        holder.txtUsername.setText(account.getUserName());
        holder.txtEmailKH.setText(account.getEmail());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Xóa thành công tài khoản: " + arrayAccount.get(position).getUserName(), Toast.LENGTH_SHORT).show();
                arrayAccount.remove(position);
                        notifyDataSetChanged();

            }
        });
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Xác nhận xóa tài khoản");
//                builder.setMessage("Bạn có chắc chắn muốn xóa tài khoản này ?");
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        arrayAccount.remove(position);
//                        notifyDataSetChanged();
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
////                        giohangAdapter.notifyDataSetChanged();
//
//                    }
//                });
//                builder.show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return arrayAccount.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder{

        public TextView txtId, txtUsername, txtEmailKH;
        public ImageButton btnDelete;


        public ItemHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtUsername= itemView.findViewById(R.id.txtUsername);
            txtEmailKH= itemView.findViewById(R.id.txtEmailKH);
            btnDelete= itemView.findViewById(R.id.btnDelete);

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
