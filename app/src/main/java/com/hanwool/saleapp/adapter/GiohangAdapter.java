package com.hanwool.saleapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanwool.saleapp.GiohangActivity;
import com.hanwool.saleapp.MainActivity;
import com.hanwool.saleapp.R;
import com.hanwool.saleapp.modal.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter{
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoldder{
        public TextView txtTengiohang, txtGiagiohang;
        public ImageView imgGiohang;
        public Button btnMinus, btnValues, btnPlus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHoldder viewHoldder = null;
        if (view == null) {
            viewHoldder = new ViewHoldder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang,null);
            viewHoldder.txtTengiohang = view.findViewById(R.id.txtTengiohang);
            viewHoldder.txtGiagiohang = view.findViewById(R.id.txtGiagiohang);
            viewHoldder.imgGiohang = view.findViewById(R.id.imgGiohang);
            viewHoldder.btnMinus = view.findViewById(R.id.btnMinus);
            viewHoldder.btnValues = view.findViewById(R.id.btnValues);
            viewHoldder.btnPlus = view.findViewById(R.id.btnPlus);
            view.setTag(viewHoldder);
        }else{
            viewHoldder = (ViewHoldder) view.getTag();
        }
        Giohang giohang = (Giohang) getItem(i);
        viewHoldder.txtTengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHoldder.txtGiagiohang.setText(decimalFormat.format(giohang.getGiasp()) + "Đ");
        Picasso.with(context).load(giohang.getHinhsp())
                .placeholder(R.drawable.imgerror)
                .error(R.drawable.imgerror)
                .into(viewHoldder.imgGiohang);
        viewHoldder.btnValues.setText(giohang.getSoluongsp() + "");
        int sl = Integer.parseInt(viewHoldder.btnValues.getText().toString());
        if (sl>=10){
            viewHoldder.btnPlus.setVisibility(View.INVISIBLE);
            viewHoldder.btnMinus.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            viewHoldder.btnMinus.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            viewHoldder.btnPlus.setVisibility(View.VISIBLE);
            viewHoldder.btnMinus.setVisibility(View.VISIBLE);
        }
        final ViewHoldder finalViewHoldder = viewHoldder;
        final ViewHoldder finalViewHoldder1 = viewHoldder;
        final ViewHoldder finalViewHoldder2 = viewHoldder;
        viewHoldder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHoldder.btnValues.getText().toString()) + 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat =(giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHoldder1.txtGiagiohang.setText(decimalFormat.format(giamoinhat) + "Đ");
                com.hanwool.saleapp.GiohangActivity.EventUltil();
                if (slmoinhat > 9) {
                    finalViewHoldder2.btnPlus.setVisibility(View.INVISIBLE);
                    finalViewHoldder2.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHoldder2.btnValues.setText(String.valueOf(slmoinhat));
                } else {
                    finalViewHoldder2.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHoldder2.btnPlus.setVisibility(View.VISIBLE);
                    finalViewHoldder2.btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        final ViewHoldder finalViewHoldder3 = viewHoldder;
        viewHoldder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHoldder.btnValues.getText().toString()) - 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat =(giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHoldder1.txtGiagiohang.setText(decimalFormat.format(giamoinhat) + "Đ");
                com.hanwool.saleapp.GiohangActivity.EventUltil();
                if (slmoinhat < 2) {
                    finalViewHoldder3.btnMinus.setVisibility(View.INVISIBLE);
                    finalViewHoldder3.btnPlus.setVisibility(View.VISIBLE);
                    finalViewHoldder3.btnValues.setText(String.valueOf(slmoinhat));
                } else {
                    finalViewHoldder3.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHoldder3.btnPlus.setVisibility(View.VISIBLE);
                    finalViewHoldder3.btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });

        return view;
    }
}
