package com.g2m.shaheen.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.views.dialogs.ScannExportItemsDialog;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    String[] titlePages;
    int [] iconPages;
    Context context;
    List<ExportProductModel>list;

    public ProductAdapter(Context cxt, List<ExportProductModel>l){
        context=cxt;
        list=l;
    }
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProductAdapter.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_model, viewGroup, false);
        viewHolder = new ProductAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, final int i) {
        holder.pro_name.setText("الاسم الاسم");

//        holder.pro_name.setText(list.get(i).name);
//        holder.pro_quantity.setText(" الكمية : "+list.get(i).quantity);
        String title="";
        if(Constants.OPERATION_TYPE==6)
            title="الكمية المجرودة";
        else if (Constants.OPERATION_TYPE==4)
            title="الكمية المصروفة";
        else if(Constants.OPERATION_TYPE==5)
            title="الكمية المحولة";
        else if(Constants.OPERATION_TYPE==1)
            title="الكمية المستلمة";
        holder.pro_quantityRecived.setText(title+" : "+12);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScann(i);
            }
        });



//        holder.pro_quantityRecived.setText(title+" : "+list.get(i).quantitiy_recivved);


    }
    public void insertAll(List<ExportProductModel> lis){
        list.clear();
        list.addAll(lis);
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {

        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pro_name,pro_quantity,pro_quantityRecived;
        public ViewHolder(View v) {
            super(v);
            pro_name=v.findViewById(R.id.pro_name);
            pro_quantity=v.findViewById(R.id.pro_quantity);
            pro_quantityRecived=v.findViewById(R.id.pro_quantityRecived);
        }

    }
    public void startScann(int pos){
        ExportProductModel exportProductModel=new ExportProductModel(1,1,"اسم الصنف",12,6);
        final ScannExportItemsDialog dialog = new ScannExportItemsDialog(context,exportProductModel);
                        //dialog.setOnDismissListener(context);
                        dialog.show();

    }

}
