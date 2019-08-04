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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.ScanningAdapter;
import com.g2m.shaheen.adapters.ScanningExportAdapter;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.viewModels.ScanningItemViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScannExportItemsDialog extends Dialog
    implements ScanningExportAdapter.ClickListener
{
    Context context;
    ScanningItemViewModel scanningItemViewModel;
    EditText barcode_scan ;
            RecyclerView scannerRes;
            boolean isShow=false;
    ScanningExportAdapter adapter;
            TextView pro_quantity,pro_quantityRecived,pro_name,enter_all,details;
            Button save;
            int counterItems=0;
              ExportProductModel product;

public ScannExportItemsDialog(Context context, ExportProductModel productModel) {
        super(context);
        this.context = context;
        this.product=productModel;
        counterItems=productModel.quantitiy_recivved;
}

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scann_export_item_layout);
        barcode_scan=findViewById(R.id.barcode_scan);
        pro_name=findViewById(R.id.pro_name);
        details=findViewById(R.id.details);
        details.setVisibility(View.GONE);
        pro_quantity=findViewById(R.id.pro_quantity);
        enter_all=findViewById(R.id.enter_all);
        pro_quantityRecived=findViewById(R.id.pro_quantityRecived);
        scanningItemViewModel= ViewModelProviders.of((FragmentActivity) context).get(ScanningItemViewModel.class);
        barcode_scan.requestFocus();
        barcode_scan.addTextChangedListener(textWatcher);
        scannerRes=findViewById(R.id.ScannerRes);
        isShow=true;
        adapter=new ScanningExportAdapter(new ArrayList<String>(),ScannExportItemsDialog.this);
         LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
         mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        scannerRes.setLayoutManager(mLayoutManager);
        scannerRes.setAdapter(adapter);
        save=findViewById(R.id.save);
       pro_quantity.setText("الكمية : "+product.quantity);
        pro_name.setText(product.name);
        pro_quantityRecived.setText("الكمية المستلمة : "+counterItems);
        save.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                    ScannExportItemsDialog.this.dismiss();
        }
        });
        enter_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterQuantity();
            }
        });
        }



    public void enterQuantity(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.enter_all_quantity);
        Button ok = dialog.findViewById(R.id.ok);
        Button cancel =  dialog.findViewById(R.id.cancel);
        final EditText barcode,quantity;
        barcode=dialog.findViewById(R.id.barcode);
        quantity=dialog.findViewById(R.id.quantity);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(barcode.getText().toString().isEmpty()||quantity.getText().toString().isEmpty())
                    Toast.makeText(context,"املء كل الخانات", Toast.LENGTH_SHORT).show();
                else if (barcode.getText().toString().length()!=20){
                    errorbarcode();
                }
                else{
                    addProducts(barcode.getText().toString(), Integer.parseInt(quantity.getText().toString()));
                    dialog.dismiss();
                }
            }
        });
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dialog.dismiss();
    }
});
        dialog.show();
    }
    public void addProducts(String barcode,int count){
        if(counterItems+count>product.quantity){
            Helper.showDialog("تحذير ","لا يمنك ادخال تلك الكمية لقد تجاوزت الكمية المطلوبة اعد المحاولة  ");
        }
        else {
            for (int i = 0; i < count; i++) {
                saveItem(barcode);
            }
        }
    }
    public void saveItem(String  barcode){
        if (counterItems>=product.quantity){
            Helper.showDialog("تحذير ","لقد قمت بادخال الكمية المطلوبة لا يمكنك ادخال عناصر اخرى ");
            barcode_scan.setText("");
        }
        else {
            product.quantitiy_recivved++;
            update(1);
            adapter.addItem(barcode);
        }

    }

  TextWatcher textWatcher = new TextWatcher() {
@Override
public void onTextChanged(CharSequence c, int i, int i1, int i2) {
    if(c.length()==20) {
        saveItem(""+c);
    }
    else {
        if(c.length()!=0) {
         errorbarcode();
        }
    }
}



@Override
public void beforeTextChanged(CharSequence c, int i, int i1, int i2) {
        }

@Override
public void afterTextChanged(Editable editable) {
        }
        };

    private void errorbarcode() {
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

    @Override
    public void delete() {
        product.quantitiy_recivved--;
        update(0);
    }
    public void update(final int type){
        scanningItemViewModel.updateExportProduct(product).observe((LifecycleOwner) context, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                barcode_scan.setText("");
                if(integer==1){
                        if(type==0) {
                            counterItems--;
                            pro_quantityRecived.setText("الكمية المستلمة : " + counterItems);
                        }
                        else {
                            counterItems++;
                            pro_quantityRecived.setText("الكمية المستلمة : "+counterItems);
                        }
                }
                else {
                    if(type==0) {
                        product.quantitiy_recivved++;
                    }
                    else {
                        product.quantitiy_recivved--;
                    }
                }
            }
        });
    }
}