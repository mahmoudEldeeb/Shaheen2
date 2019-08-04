package com.g2m.shaheen.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.ProductAdapter;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.viewModels.ProductViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
int op_number=0;
    RecyclerView res_product;
    ProductAdapter adapter;
    ProductViewModel productViewModel;
    List<ExportProductModel>exportProductModels=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        op_number=getIntent().getIntExtra(Constants.OPERATION_NUMBER,0);
        adapter=new ProductAdapter(this,new ArrayList<ExportProductModel>());
        res_product=findViewById(R.id.res_product);
        Constants.context=this;
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        res_product.setLayoutManager(layoutManager);
        res_product.setAdapter(adapter);
        productViewModel= ViewModelProviders.of(this).get(ProductViewModel.class);
        if(Helper.isNetworkAvailable()) {
            getONlineProducts();
        }
        getOfflineProducts();


        /*List<ExportProductModel>list=new ArrayList<>();
        for(int i=0;i<7;i++){
            ExportProductModel operationsModel=new ExportProductModel(i,op_number,"asdasd",5);
            list.add(operationsModel);
        }*/
        getOfflineProducts();
       // productViewModel.insertAllExportsProducts(list);

    }

    private void getONlineProducts() {
        Helper.showDialog();
        productViewModel.getProductsOfOperations(op_number).observe(this, new Observer<List<ExportProductModel>>() {
            @Override
            public void onChanged(List<ExportProductModel> exportProductModels) {
                Helper.dismiss();
             //   Log.v("wwwwd",exportProductModels.size()+"");
                productViewModel.insertAllExportsProducts(exportProductModels);
            }
        });
    }

    private void  getOfflineProducts() {
    productViewModel.getOfflineProducts(op_number).observe(this, new Observer<List<ExportProductModel>>() {
        @Override
        public void onChanged(List<ExportProductModel> exportodels) {
            exportProductModels=exportodels;
            adapter.insertAll(exportodels);
        }
    });
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
        if(exportProductModels.size()>0){
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObj1 = new JSONObject();
            try {
                for(int i=0;i<exportProductModels.size();i++) {
                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("product_id", exportProductModels.get(i).id);
                        jsonObj.put("product_qty", exportProductModels.get(i).quantitiy_recivved);
                        jsonArray.put(jsonObj);

                }

                //  jsonObj1.put("asd",jsonArray);
                Log.v("oooooo",op_number+"      "+jsonArray.toString());
                    productViewModel.sendDataToServer(jsonArray.toString(),op_number);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Helper.showDialog("تحذير ","لا يوجد داتا لتقوم بعملية الحفظ");
        }
    }

}
