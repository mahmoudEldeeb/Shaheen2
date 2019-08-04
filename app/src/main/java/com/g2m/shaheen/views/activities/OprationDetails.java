package com.g2m.shaheen.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.ItemsAdapter;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.viewModels.OperationDetailsViewModdel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OprationDetails extends AppCompatActivity {
    RecyclerView items_res;
    ItemsAdapter adapter;
    int operationNumber;
    Button complete_operation;
    OperationDetailsViewModdel operationDetailsViewModdel;
    List<ScanningItemModel> scanningItemModelList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opration_details);
        Constants.context=this;
        items_res=findViewById(R.id.items_res);
        complete_operation=findViewById(R.id.complete_operation);
        operationNumber=getIntent().getIntExtra(Constants.OPERATION_NUMBER,0);
        operationDetailsViewModdel= ViewModelProviders.of(this).get(OperationDetailsViewModdel.class);
        adapter=new ItemsAdapter(new ArrayList<ScanningItemModel>());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        items_res.setLayoutManager(layoutManager);
        items_res.setHasFixedSize(true);
        items_res.setAdapter(adapter);
        operationDetailsViewModdel.getScanningItems(operationNumber).observe(this, new Observer<List<ScanningItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ScanningItemModel> scanningItemModels) {
                adapter.addItems(scanningItemModels);
            scanningItemModelList=scanningItemModels;
            }

        });
        complete_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OprationDetails.this,ReciveActivity.class);
                intent.putExtra(Constants.OPERATION_NUMBER,operationNumber);
                startActivity(intent);
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
        if(scanningItemModelList.size()>0){
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObj1 = new JSONObject();
            try {
                for(int i=0;i<scanningItemModelList.size();i++) {
                    JSONObject jsonObj = new JSONObject();

                    jsonObj.put("operation_id", scanningItemModelList.get(i).operationId);
                    jsonObj.put("stock_id", scanningItemModelList.get(i).stockId);
                    jsonObj.put("category_id", scanningItemModelList.get(i).catId);
                    jsonObj.put("product_id", scanningItemModelList.get(i).productId);
                    jsonObj.put("package_barcode", scanningItemModelList.get(i).barcode);
                    jsonObj.put("barcode", scanningItemModelList.get(i).barcode);
                    jsonObj.put("product_name",scanningItemModelList.get(i).product_name);
                    jsonArray.put(jsonObj);

                }

                //  jsonObj1.put("asd",jsonArray);
                Log.v("oooooo",jsonArray.toString());
               // operationDetailsViewModdel.sendDataToServer(jsonArray.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Helper.showDialog("تحذير ","لا يوجد داتا لتقوم بعملية الحفظ");
        }
    }
}
