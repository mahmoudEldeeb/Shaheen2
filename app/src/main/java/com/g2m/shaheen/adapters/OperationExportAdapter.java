package com.g2m.shaheen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.g2m.shaheen.R;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.views.activities.ProductActivity;

import java.util.ArrayList;
import java.util.List;

public class OperationExportAdapter extends RecyclerView.Adapter<OperationExportAdapter.ViewHolder> {


    Context context;
List<OperationsModel>list=new ArrayList<>();

    public OperationExportAdapter(Context cxt, List<OperationsModel>l){
        context=cxt;
        list=l;

    }
    @NonNull
    @Override
    public OperationExportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        OperationExportAdapter.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_operation_model, viewGroup, false);
        viewHolder = new OperationExportAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull OperationExportAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.op_number.setText("اسم الحركة");
        viewHolder.op_date.setText("تاريخ العملية  :  "+"12/45/2122");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductActivity.class);
                intent.putExtra(Constants.OPERATION_NUMBER,1);
                     context.startActivity(intent);
            }
        });
//        viewHolder.op_number.setText(list.get(i).name);
//        viewHolder.op_date.setText("تاريخ العملية  :  "+list.get(i).min_date);
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, ProductActivity.class);
//                intent.putExtra(Constants.OPERATION_NUMBER,list.get(i).id);
//                context.startActivity(intent);
//            }
//        });
    }
    public void insertAll(List<OperationsModel> lis){
        list.clear();
        list.addAll(lis);
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView op_number,op_date;
        public ViewHolder(View v) {
            super(v);
            op_number=v.findViewById(R.id.op_number);
            op_date=v.findViewById(R.id.op_date);
        }

    }
}
