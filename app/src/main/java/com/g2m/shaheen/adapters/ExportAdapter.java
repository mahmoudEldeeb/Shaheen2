package com.g2m.shaheen.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.views.activities.OprationDetails;
import com.g2m.shaheen.views.activities.ReciveActivity;
import com.g2m.shaheen.views.dialogs.ScannItemsDialog;

import java.util.List;

public class ExportAdapter extends RecyclerView.Adapter<ExportAdapter.ViewHolder> {
    List<Integer> list;
    Context context;
    public ExportAdapter(Context cxt) {
        context=cxt;
    }

    @NonNull
    @Override
    public ExportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ExportAdapter.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_export_model, viewGroup, false);
        viewHolder = new ExportAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ExportAdapter.ViewHolder viewHolder, final int i) {

       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startScann();
           }
       });

    }
    public void startScann(){
        final ScannItemsDialog dialog = new ScannItemsDialog(context);
                        dialog.setCounterItems(2);
                        dialog.show();
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;


        public ViewHolder(View v) {
            super(v);
          //  title = v.findViewById(R.id.title);

        }

    }
}
