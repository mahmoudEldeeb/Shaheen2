package com.g2m.shaheen.views.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.ItemsAdapter;
import com.g2m.shaheen.adapters.OperationAdapter;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.viewModels.OperationViewModel;

import java.util.ArrayList;
import java.util.List;

public class OperationsActivity extends AppCompatActivity {
    RecyclerView res_opreration;
    OperationAdapter adapter;
    OperationViewModel operationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

        operationViewModel= ViewModelProviders.of(this).get(OperationViewModel.class);
        res_opreration=findViewById(R.id.res_opreration);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        res_opreration.setLayoutManager(layoutManager);
        operationViewModel.getAllOprations().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(@Nullable List<Integer> integers) {
                adapter=new OperationAdapter(OperationsActivity.this,integers);
                res_opreration.setAdapter(adapter);
            }
        });


    }

}
