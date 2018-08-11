package com.hanwool.saleapp.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hanwool.saleapp.BrandPhoneDetailsActivity.Huawei;
import com.hanwool.saleapp.BrandPhoneDetailsActivity.Iphone;
import com.hanwool.saleapp.BrandPhoneDetailsActivity.Nokia;
import com.hanwool.saleapp.BrandPhoneDetailsActivity.Oppo;
import com.hanwool.saleapp.BrandPhoneDetailsActivity.Samsung;
import com.hanwool.saleapp.BrandPhoneDetailsActivity.Xiaomi;
import com.hanwool.saleapp.R;

import com.hanwool.saleapp.modal.Loaisp;

import com.hanwool.saleapp.modal.Sanpham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends RecyclerView.Adapter<LoaispAdapter.ItemHolder> {
    Context context;
    ArrayList<Loaisp> arrayLoaisp;
    ArrayList<Sanpham> arraySanpham;
    SanPhamtrongLoaispAdapter sanPhamtrongLoaispAdapter;
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
//    private void getIphone() {
//        RequestQueue requestQueue= Volley.newRequestQueue(context);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanIphone, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response != null){
//                    int ID = 0;
//                    String Tensp = "";
//                    Integer Giasp=0;
//                    String Hinhanhsp= "";
//                    String Motasp="";
//                    int idlsp = 0;
//                    for (int i =0; i<response.length(); i++){
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            ID = jsonObject.getInt("id");
//                            Tensp= jsonObject.getString("tensanpham");
//                            Giasp= jsonObject.getInt("giasanpham");
//                            Hinhanhsp= jsonObject.getString("hinhanhsanpham");
//                            Motasp= jsonObject.getString("motasanpham");
//                            idlsp= jsonObject.getInt("idloaisanpham");
//                            arraySanpham.add(new Sanpham(ID,Tensp,Giasp,Hinhanhsp,Motasp,idlsp));
//                            sanPhamtrongLoaispAdapter.notifyDataSetChanged();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }
    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        Loaisp loaisp = arrayLoaisp.get(position);
        holder.txtBrandName.setText(loaisp.getTenloaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.logo)
                .error(R.drawable.imgerror)
                .into(holder.imgBrandPhone);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                switch (position){
                    case 0:
                        Intent i0 = new Intent(context, Iphone.class);
                       context.startActivity(i0);
                        break;
                    case 1:
                        Intent i1 = new Intent(context, Samsung.class);
                        context.startActivity(i1);
                        break;
                    case 2:
                        Intent i2 = new Intent(context, Nokia.class);
                        context.startActivity(i2);
                        break;
                    case 3:
                        Intent i3 = new Intent(context, Huawei.class);
                        context.startActivity(i3);
                        break;
                    case 4:
                        Intent i4 = new Intent(context, Xiaomi.class);
                        context.startActivity(i4);
                        break;
                    case 5:
                        Intent i5 = new Intent(context, Oppo.class);
                        context.startActivity(i5);
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayLoaisp.size();
    }

    public class  ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgBrandPhone;
        public TextView txtBrandName;
        private ItemClickListener itemClickListener;

        public ItemHolder(View itemView) {
            super(itemView);
            imgBrandPhone = itemView.findViewById(R.id.imgPhoneBrand);
            txtBrandName= itemView.findViewById(R.id.txtPhoneBrand);
            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }


        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition());
        }
    }
    public interface ItemClickListener {
        void onClick(View view, int position);
    }

}

