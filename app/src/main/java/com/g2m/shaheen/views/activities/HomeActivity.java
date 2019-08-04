package com.g2m.shaheen.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.g2m.shaheen.R;
import com.g2m.shaheen.adapters.PagesAdapter;


public class HomeActivity extends AppCompatActivity {

    PagesAdapter pagesAdapter;
RecyclerView res_pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        res_pages=findViewById(R.id.res_pages);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        res_pages.setLayoutManager(layoutManager);
        res_pages.setNestedScrollingEnabled(false);
        pagesAdapter=new PagesAdapter(this);
        res_pages.setAdapter(pagesAdapter);
    }
}
