package com.g2m.shaheen.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.g2m.shaheen.R;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.viewModels.ProcessViewModel;

import java.util.Calendar;
import java.util.Date;


public class ProcessActivity extends AppCompatActivity {
CardView new_process;
ProcessViewModel processViewModel;
int operationNumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        new_process=findViewById(R.id.new_process);
        processViewModel= ViewModelProviders.of(this).get(ProcessViewModel.class);
        new_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProcessActivity.this,ReciveActivity.class);
                intent.putExtra(Constants.OPERATION_NUMBER,operationNumber);
                startActivity(intent);
            }
        });
        findViewById(R.id.old_process).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProcessActivity.this,OperationsActivity.class);
                startActivity(intent);
            }
        });
        processViewModel.getLastOperation().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
               try {
                   operationNumber = integer + 1;
               }catch (NullPointerException e){
                   operationNumber=0;
               }
            }
        });

    }
}
