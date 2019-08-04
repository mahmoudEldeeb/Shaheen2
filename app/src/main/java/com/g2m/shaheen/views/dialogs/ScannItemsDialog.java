package com.g2m.shaheen.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.ScanningAdapter;
import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.PackageModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ScannModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.models.dataModels.StockModel;
import com.g2m.shaheen.models.sharedPrefrence.SaveDataInPrefrence;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.viewModels.ScanningItemViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScannItemsDialog extends Dialog
    implements ScanningAdapter.ClickListener
{
    Context context;
    EditText barcode_scan ;
            RecyclerView scannerRes;
            boolean isShow=false;
            ScanningAdapter adapter;
            TextView counter;
            Button save;
            int counterItems=0;
            StockModel stock;CategoryModel category;ProductModel product;
            int operation_number;
            String package_barcode;
            ScanningItemViewModel scanningItemViewModel;
public ScannItemsDialog(Context context, String package_barcode, StockModel stock,
                        CategoryModel pack, ProductModel pro, int operation_number) {
        super(context);
        this.context = context;
        this.stock=stock;
        this.category=pack;
        this.product=pro;
        this.package_barcode=package_barcode;
        this.operation_number=operation_number;

}

    public ScannItemsDialog(Context context) {
        super(context);
        this.context=context;
    }

    public void setCounterItems(int counterItems) {
        this.counterItems = counterItems;
    }

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scann_item_layout);
        barcode_scan=findViewById(R.id.barcode_scan);
          counter=findViewById(R.id.counter);
          scanningItemViewModel= ViewModelProviders
            .of((FragmentActivity)context)
            .get(ScanningItemViewModel.class);
        barcode_scan.requestFocus();
        barcode_scan.addTextChangedListener(textWatcher);
        scannerRes=findViewById(R.id.ScannerRes);
        isShow=true;
        adapter=new ScanningAdapter(new ArrayList<ScanningItemModel>(),scanningItemViewModel,ScannItemsDialog.this);
         LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
         mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        scannerRes.setLayoutManager(mLayoutManager);
        scannerRes.setAdapter(adapter);
        save=findViewById(R.id.save);
        counter.setText(counterItems+"/50");
        save.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                    ScannItemsDialog.this.dismiss();
        }
        });
        }

    public boolean isShow() {
        return isShow;
    }

    private void saveData() {
        barcode_scan.setEnabled(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("تم تحميل الباليته");
        builder.setMessage("تم هل تريد حفظ العناصر ");
        builder.setPositiveButton("نعم", new OnClickListener() {
@Override
public void onClick(DialogInterface di, int i) {
        di.dismiss();
        }
        });
        builder.setNegativeButton("الغاء", null);
        builder.show();

        }

  TextWatcher textWatcher = new TextWatcher() {
@Override
public void onTextChanged(CharSequence c, int i, int i1, int i2) {
       if(c.length()==20) {
           saveItem(""+c);
       }
       else {
           if(c.length()!=0) {
               //   Helper.showDialog("تحذير","قم بادخال رقم صحيح");

               AlertDialog.Builder builder = new AlertDialog.Builder(Constants.context);
               builder.setTitle("تحذير");
               builder.setMessage("قم بادخال رقم صحيح");
               builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface di, int i) {
                       di.dismiss();
                       barcode_scan.setText("");
                   }
               });
               builder.show();
           }
       }
        //if(counterItems==4){
        //saveData();

        }ScanningItemModel scanModel;
        public void saveItem(String  barcode){
              scanModel=new ScanningItemModel(barcode,package_barcode,
                    stock.id,category.id,product.id,
                    operation_number,stock.name,category.name,product.name);
            scanningItemViewModel.saveItem(scanModel).observe((LifecycleOwner) context, new Observer<Long>() {
                @Override
                public void onChanged(@Nullable Long aLong) {
                    if(aLong>-1){
                        long l = aLong;
                        int i = (int) l;
                        scanModel.id= i;
                        adapter.addItem(scanModel);
                        scannerRes.smoothScrollToPosition(adapter.getItemCount()-1);
                        barcode_scan.setText("");
                        counterItems++;
                        counter.setText(counterItems+"/50");

                    }
                }
            });




}

@Override
public void beforeTextChanged(CharSequence c, int i, int i1, int i2) {
        }

@Override
public void afterTextChanged(Editable editable) {

        }
        };

    @Override
    public void delete() {
        counterItems--;
        counter.setText(counterItems+"/50");

    }
}