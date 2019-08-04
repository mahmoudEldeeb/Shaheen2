package com.g2m.shaheen.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.ExportAdapter;
import com.g2m.shaheen.adapters.OperationExportAdapter;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.models.dataModels.ResultModel.OperationsResult;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.viewModels.ExportViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExportActivity extends AppCompatActivity {
    OperationExportAdapter exportAdapter;
    RecyclerView res_export;
    ExportViewModel exportViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        Constants.context=this;
        exportAdapter=new OperationExportAdapter(this,new ArrayList<OperationsModel>());
        res_export=findViewById(R.id.res_export);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        res_export.setLayoutManager(layoutManager);
        res_export.setAdapter(exportAdapter);
        exportViewModel= ViewModelProviders.of(this).get(ExportViewModel.class);
        if(Helper.isNetworkAvailable()) {
            getOnLineOperations();
        }
       /* List<OperationsModel>list=new ArrayList<>();
        for(int i=0;i<6;i++){
            OperationsModel operationsModel=new OperationsModel(i,"asdasd","12/23/3333");
            list.add(operationsModel);
        }
        exportViewModel.insertAlloexportOperations(list);
       */
       getOfflineOperations();

    }

    private void getOnLineOperations() {
        Helper.showDialog();
    exportViewModel.getoperations().observe(this, new Observer<OperationsResult>() {
        @Override
        public void onChanged(OperationsResult operationsResult) {
            if(operationsResult.getStock_out()!=null) {
                if (operationsResult.getStock_out().size() > 0) {
                    exportViewModel.insertAlloexportOperations(operationsResult.getStock_out());
                } else {

                }
            }
            Helper.dismiss();

        }
    });
    }

    private void getOfflineOperations() {
    exportViewModel.getOfflineOperation().observe(this, new Observer<List<OperationsModel>>() {
        @Override
        public void onChanged(List<OperationsModel> operationsModels) {
            exportAdapter.insertAll(operationsModels);
            if(operationsModels.size()==0){
               // Helper.showDialog("عفوا","لا يوجد بيانات");
            }
        }
    });
    }
}
