package com.g2m.shaheen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.views.activities.ExportActivity;
import com.g2m.shaheen.views.activities.ProcessActivity;

import java.util.List;

public class PagesAdapter extends RecyclerView.Adapter<PagesAdapter.ViewHolder> {
String[] titlePages;
int [] iconPages;
Context context;


public PagesAdapter (Context cxt){
    context=cxt;
    titlePages=context.getResources().getStringArray(R.array.pages_name);
    iconPages= new int[]{R.drawable.stock_out, R.drawable.stock_recive, R.drawable.gard, R.drawable.stock_trans,R.drawable.stock_direct_recive};
}
@NonNull
@Override
public PagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
        }


@Override
public void onBindViewHolder(@NonNull PagesAdapter.ViewHolder viewHolder, final int i) {

   viewHolder.title.setText(titlePages[i]);
viewHolder.image.setImageResource(iconPages[i]);
viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       if(i==0){
           Constants.OPERATION_TYPE=4;
           context.startActivity(new Intent(context, ExportActivity.class));
       }
        else if(i==1){
            context.startActivity(new Intent(context, ProcessActivity.class));
        }
        else if(i==2){
           Constants.OPERATION_TYPE=6;
           context.startActivity(new Intent(context, ExportActivity.class));
       }
        else if(i==3){
            Constants.OPERATION_TYPE=5;
            context.startActivity(new Intent(context, ExportActivity.class));
        }
       else if(i==4){
           Constants.OPERATION_TYPE=1;
           context.startActivity(new Intent(context, ExportActivity.class));
       }

    }
});

}
@Override
public int getItemCount() {
        return 5;
        }

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
ImageView image;

    public ViewHolder(View v) {
        super(v);
        title = v.findViewById(R.id.title);
        image = v.findViewById(R.id.image);

    }

}
}
