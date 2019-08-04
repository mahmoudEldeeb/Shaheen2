package com.g2m.shaheen.viewModels;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.g2m.shaheen.models.Repositry;
import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ResultModel.ProductDataResult;
import com.g2m.shaheen.models.dataModels.StockModel;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.views.activities.HomeActivity;

import java.io.IOException;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class SplashViewModel extends ViewModel {
     public MutableLiveData<ProductDataResult>getProductData()
     {
         final MutableLiveData<ProductDataResult>result=new MutableLiveData<>();
         Repositry.getProductsData().subscribeWith(new SingleObserver<ProductDataResult>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onSuccess(ProductDataResult productDataResult) {
                    result.setValue(productDataResult);

             }

             @Override
             public void onError(Throwable e) {

                 Constants.context.startActivity(new Intent(Constants.context, HomeActivity.class));
                 Activity activity= (Activity) Constants.context;
                 activity.finish();
             }
         });
         return result;
     }

     public void insertAllProductsData(List<ProductModel>items,final List<CategoryModel>cats,
                                       final List<StockModel>stocks){
         Repositry.insertAllItems(items,cats,stocks);
     }
    }
