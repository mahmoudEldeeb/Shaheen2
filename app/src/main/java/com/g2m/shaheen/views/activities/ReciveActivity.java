package com.g2m.shaheen.views.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.ItemsAdapter;
import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.models.dataModels.StockModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.viewModels.ReciveViewModel;
import com.g2m.shaheen.views.dialogs.ScannItemsDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReciveActivity extends AppCompatActivity implements DialogInterface.OnDismissListener{
EditText package_barcode;
    ReciveViewModel reciveViewModel;
    Button start_scann;
    Spinner spinner_stock,spinner_cats,spinner_product;
    List<ScanningItemModel>scanningItemModelList=new ArrayList<>();
    List<ScanningItemModel>updatedItemModelList=new ArrayList<>();
    List<StockModel> stockModelList=new ArrayList<>();
    List<CategoryModel> catsModelList=new ArrayList<>();
    List<ProductModel> productModelList=new ArrayList<>();
    RecyclerView items_res;
    ItemsAdapter adapter;
    int operationNumber=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive);
        package_barcode=findViewById(R.id.package_barcode);
        //package_barcode.addTextChangedListener(textWatcher);
        start_scann=findViewById(R.id.start_scann);
        spinner_stock=findViewById(R.id.spinner_stock);
        spinner_product=findViewById(R.id.spinner_product);
        spinner_cats=findViewById(R.id.spinner_cats);
        items_res=findViewById(R.id.items_res);
        operationNumber=getIntent().getIntExtra(Constants.OPERATION_NUMBER,0);
        adapter=new ItemsAdapter(new ArrayList<ScanningItemModel>());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        items_res.setLayoutManager(layoutManager);
        items_res.setAdapter(adapter);

        Constants.context=this;
        reciveViewModel= ViewModelProviders.of(this).get(ReciveViewModel.class);
        start_scann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(package_barcode.getText().toString().length()==20)
                    startScann();
                else Helper.showDialog("رقم غير صحيح","من فضلك قم بادخال رقم باليته صحيح");
            }
        });
        getSpinersData();
        getScanningItems();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recive_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if(item.getItemId()==R.id.save)
                 saveDataToServer();
        return true;
    }
    public void saveDataToServer(){
            if(scanningItemModelList.size()>0){
                JSONArray jsonArray=new JSONArray();
                JSONObject jsonObj1 = new JSONObject();
                try {
                    updatedItemModelList.clear();
                    for(int i=0;i<scanningItemModelList.size();i++) {
                        if(scanningItemModelList.get(i).senOrNot==false) {
                            JSONObject jsonObj = new JSONObject();
                            jsonObj.put("operation_id", scanningItemModelList.get(i).operationId);
                            jsonObj.put("stock_id", scanningItemModelList.get(i).stockId);
                            jsonObj.put("category_id", scanningItemModelList.get(i).catId);
                            jsonObj.put("product_id", scanningItemModelList.get(i).productId);
                            jsonObj.put("package_barcode", scanningItemModelList.get(i).barcode);
                            jsonObj.put("barcode", scanningItemModelList.get(i).barcode);
                            jsonObj.put("product_name", scanningItemModelList.get(i).product_name);
                            jsonArray.put(jsonObj);
                            ScanningItemModel scanningItemModel=scanningItemModelList.get(i);
                            scanningItemModel.senOrNot=true;
                            updatedItemModelList.add(scanningItemModel);
                        }
                    }

              //  jsonObj1.put("asd",jsonArray);
                Log.v("oooooo",jsonArray.toString());
                    if(updatedItemModelList.size()==0){
                        Helper.showDialog(" تحذير","لم تقم باى تعديل او اضافة لنقوم بحفظها");
                    }else
                           reciveViewModel.sendDataToServer(jsonArray.toString()).observe(this, new Observer<Integer>() {
                               @Override
                               public void onChanged(Integer integer) {
                                   if(integer==1){
                                       updateData();
                                   }
                               }
                           });
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Helper.showDialog("تحذير ","لا يوجد داتا لتقوم بعملية الحفظ");
            }
    }

   public void updateData(){
        Helper.showDialog();
        reciveViewModel.updateData(updatedItemModelList).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer longs) {
                if(longs==updatedItemModelList.size()){
                    Log.v("aaaaa","ddgyhhyhhhyhy");
                }
                else {

                }
                Helper.dismiss();
            }
        });
    }
    public void getScanningItems(){
        reciveViewModel.getScanningItems(operationNumber).observe(this, new Observer<List<ScanningItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ScanningItemModel> scanningItemModels) {
                adapter.addItems(scanningItemModels);
                scanningItemModelList.clear();
                scanningItemModelList=scanningItemModels;
                Log.v("qqq",scanningItemModels.size()+"");

            }
        });

    }
    public void getSpinersData(){
        final List<String> stockList = new ArrayList<String>();
        stockList.add("aaaaa");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, stockList);
        StockModel s=new StockModel();
        s.id=1;s.name="bbbb";
        stockModelList.add(s);
        Log.v("ssssss",stockModelList.size()+"");
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_stock.setAdapter(dataAdapter);
        reciveViewModel.getAllStocks().observe(this, new Observer<List<StockModel>>() {
            @Override
            public void onChanged(@Nullable List<StockModel> stockModels) {
              // stockModelList=stockModels;
                for (int i=0;i<stockModels.size();i++){
                    stockList.add(stockModels.get(i).name);
                }
                dataAdapter.notifyDataSetChanged();
            }
        });

        final List<String> catsList = new ArrayList<String>();
        catsList.add("bbbbb");
        CategoryModel c=new CategoryModel();
        c.id=3;
        c.name="bbbb";
        catsModelList.add(c);
        final ArrayAdapter<String> catsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, catsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cats.setAdapter(catsAdapter);
        reciveViewModel.getAllCats().observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(@Nullable List<CategoryModel> categoryModels) {
                //catsModelList=categoryModels;
                for (int i=0;i<categoryModels.size();i++){
                    catsList.add(categoryModels.get(i).name);
                }
                catsAdapter.notifyDataSetChanged();
            }
        });



        final List<String> productsList = new ArrayList<String>();
productsList.add("dddd");
        final ArrayAdapter<String> productsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, productsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProductModel p=new ProductModel();
        p.id=0;
        p.name="aaa";
        spinner_product.setAdapter(productsAdapter);
        productModelList.add(p);
        reciveViewModel.getAllProducts().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(@Nullable List<ProductModel> productsModels) {
                //productModelList=productsModels;
                for (int i=0;i<productsModels.size();i++){
                    productsList.add(productsModels.get(i).name);
                }
                productsAdapter.notifyDataSetChanged();
            }
        });
    }

    public void startScann(){
        String bar=package_barcode.getText().toString();
    Log.v("mmmmm",bar.substring(10,12));
//    final ScannItemsDialog dialog = new ScannItemsDialog(ReciveActivity.this
//            ,package_barcode.getText().toString(),
//            stockModelList.get(spinner_stock.getSelectedItemPosition())
//            ,catsModelList.get(spinner_cats.getSelectedItemPosition()),
//            productModelList.get(spinner_product.getSelectedItemPosition()),operationNumber);
//
                final ScannItemsDialog dialog = new ScannItemsDialog(ReciveActivity.this
                ,package_barcode.getText().toString(),
                stockModelList.get(spinner_stock.getSelectedItemPosition())
                ,catsModelList.get(spinner_cats.getSelectedItemPosition()),
                productModelList.get(spinner_product.getSelectedItemPosition()),operationNumber);


        reciveViewModel.getItemCount(package_barcode.getText().toString(),operationNumber).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if(!dialog.isShow())
                {
                     if(integer>=10)
                     {
                           Helper.showDialog("تحذير","لقد قمت بادخال عناصر هذه الباليته من قبل ");
                              package_barcode.setText("");
                     }
                     else {
                         dialog.setCounterItems(integer);
                         dialog.setOnDismissListener(ReciveActivity.this);
                         dialog.show();
                     }
                }
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
    }
}