package com.g2m.shaheen.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    List<ScanningItemModel> list;

    public ItemsAdapter(List<ScanningItemModel> list) {
        this.list = list;
        setHasStableIds(true);
    }
    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_model, viewGroup, false);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder viewHolder, int i) {

if(i==0){
String bar=list.get(i).barcode;
    viewHolder.package_barcode.setVisibility(View.VISIBLE);
    viewHolder.package_barcode.setText("رقم الباليته "+ list.get(i).packageBarcode);
}
      else if(!list.get(i).packageBarcode.equals(list.get(i-1).packageBarcode)){
            viewHolder.package_barcode.setVisibility(View.VISIBLE);
            viewHolder.package_barcode.setText("رقم الباليته "+ list.get(i).packageBarcode);
        }

       viewHolder.item_name.setText(list.get(i).barcode.substring(0,5));
        viewHolder.item_date.setText(list.get(i).barcode.substring(5,10));
        viewHolder.item_weight.setText(list.get(i).barcode.substring(10,15)+"");
        viewHolder.magzar_name.setText(list.get(i).barcode.substring(15,20));

        viewHolder.item_cat.setText(list.get(i).cat_name);
        viewHolder.item_product.setText(list.get(i).product_name);
        viewHolder.item_stock.setText(list.get(i).stock_name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addItem(ScanningItemModel item){
        list.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<ScanningItemModel> scanningItemModels) {
        list.clear();
        list.addAll(scanningItemModels);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView item_weight,item_date,package_barcode,item_name
                ,item_product,item_cat,item_stock,magzar_name;
        LinearLayout data,titles;


        public ViewHolder(View v) {
            super(v);
           // data = v.findViewById(R.id.data);
            titles = v.findViewById(R.id.titles);
            package_barcode=v.findViewById(R.id.package_barcode);
            item_weight = v.findViewById(R.id.item_weight);
            item_date = v.findViewById(R.id.item_date);
            item_name = v.findViewById(R.id.item_name);
            magzar_name=v.findViewById(R.id.magzar_name);
            item_product=v.findViewById(R.id.item_product);
            item_cat=v.findViewById(R.id.item_cat);
            item_stock=v.findViewById(R.id.item_stock);

        }

    }
}
