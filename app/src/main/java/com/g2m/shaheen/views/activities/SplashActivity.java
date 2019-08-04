package com.g2m.shaheen.views.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.g2m.shaheen.R;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ResultModel.ProductDataResult;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;
import com.g2m.shaheen.viewModels.SplashViewModel;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    SplashViewModel splashViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashViewModel= ViewModelProviders.of(this).get(SplashViewModel.class);
        Constants.context=this;
        if(Helper.isNetworkAvailable()) {

            getProducts();
        }
        else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startACtivity();
                }
            }, 1000);
        }

    }
    public void startACtivity(){
        startActivity(new Intent(SplashActivity.this, HomeActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        overridePendingTransition(R.anim.push_up_enter, R.anim.push_up_exit);
        finish();
    }
    public void getProducts() {

        splashViewModel.getProductData().observe(this, new Observer<ProductDataResult>() {
            @Override
            public void onChanged(@Nullable ProductDataResult productDataResult) {

                if(productDataResult.getProduct().size()>0){
                    splashViewModel.insertAllProductsData(productDataResult.getProduct(),
                            productDataResult.getCategory(),productDataResult.getStock());

                    startACtivity();

                  }
                else {

                    startACtivity();


                }
            }
        });
    }

}
