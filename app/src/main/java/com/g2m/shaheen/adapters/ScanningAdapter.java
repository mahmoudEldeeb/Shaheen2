package com.g2m.shaheen.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ScannModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.viewModels.ScanningItemViewModel;
import com.g2m.shaheen.views.dialogs.ScannItemsDialog;

import java.util.List;

public class ScanningAdapter extends RecyclerView.Adapter<ScanningAdapter.ViewHolder> {
    List<ScanningItemModel> list;
    ScanningItemViewModel scanningItemViewModel;
    ScannItemsDialog context;
    public interface ClickListener{
        public void delete();
    }
    public ScanningAdapter(List<ScanningItemModel> list, ScanningItemViewModel s, ScannItemsDialog ctx) {
        this.context=ctx;
        this.list = list;
        scanningItemViewModel=s;
    }

    @NonNull
    @Override
    public ScanningAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_scanning, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ScanningAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.barcode.setText(list.get(i).barcode);
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(i);
                list.remove(i);
                notifyDataSetChanged();
            }
        });
    }
    private void removeItem(int pos) {
        scanningItemViewModel.deleteItem(list.get(pos));
        ClickListener clickListener=  this.context;
        clickListener.delete();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(ScanningItemModel item) {
        list.add(item);
        notifyDataSetChanged();
    }
    public List<ScanningItemModel> getItems() {
        return list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView barcode,name;
        ImageButton delete;
        public ViewHolder(View v) {
            super(v);
            delete = v.findViewById(R.id.delete);
            barcode = v.findViewById(R.id.barcode);
        }
    }
}