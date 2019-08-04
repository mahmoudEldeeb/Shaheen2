package com.g2m.shaheen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.viewModels.ScanningItemViewModel;
import com.g2m.shaheen.views.dialogs.ScannExportItemsDialog;
import com.g2m.shaheen.views.dialogs.ScannItemsDialog;

import java.util.List;

public class ScanningExportAdapter extends RecyclerView.Adapter<ScanningExportAdapter.ViewHolder> {
    List<String> list;
    ScanningItemViewModel scanningItemViewModel;
    ScannExportItemsDialog context;
    public interface ClickListener{
        public void delete();
    }
    public ScanningExportAdapter(List<String> list, ScannExportItemsDialog ctx) {
        this.context=ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ScanningExportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_scanning, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ScanningExportAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.barcode.setText(list.get(i));
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
        ClickListener clickListener= (ClickListener) this.context;
        clickListener.delete();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(String item) {
        list.add(item);
        notifyDataSetChanged();
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