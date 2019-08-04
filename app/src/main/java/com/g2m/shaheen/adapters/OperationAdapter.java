package com.g2m.shaheen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.views.activities.OprationDetails;

import java.util.List;

public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.ViewHolder> {
    List<Integer> list;
Context context;
    public OperationAdapter(Context cxt,List<Integer> list) {
        this.list = list;
        context=cxt;
    }

    @NonNull
    @Override
    public OperationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_operation, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull OperationAdapter.ViewHolder viewHolder, final int i) {
            viewHolder.title.setText("استلام رقم "+list.get(i));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, OprationDetails.class);
                    intent.putExtra(Constants.OPERATION_NUMBER,list.get(i));
                    context.startActivity(intent
                    );
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;


        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);

        }

    }
}
