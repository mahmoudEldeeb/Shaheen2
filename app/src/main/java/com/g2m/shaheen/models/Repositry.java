package com.g2m.shaheen.models;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.Result;
import com.g2m.shaheen.models.dataModels.ResultModel.OperationsResult;
import com.g2m.shaheen.models.dataModels.ResultModel.ProductDataResult;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.models.dataModels.StockModel;
import com.g2m.shaheen.models.database.DataDao;
import com.g2m.shaheen.models.database.LocalDatabase;
import com.g2m.shaheen.models.network.RetrofitConnection;
import com.g2m.shaheen.utils.Constants;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Repositry {

    private static DataDao dataDao;
    public Repositry(){

    }
    public static DataDao getDao(){
        LocalDatabase db = LocalDatabase.getDatabase();
        return db.dao();
    }
    public static LiveData<Integer>isExist(String barcode){
        return getDao().isExist(barcode);
    }

    public static LiveData<List<ScanningItemModel>>getScanningItems(int op_id){
        return getDao().getScanningItems(op_id);
    }

    public static LiveData<Integer> getItemCount(String code, int operationNumber){
        return getDao().getItemCount(code,operationNumber);
    }
    public static LiveData<List<OperationsModel>> getEXportOperations(){
        return getDao().getEXportOperations(Constants.OPERATION_TYPE);
    }

    public static Single<ProductDataResult> getProductsData(){
        return RetrofitConnection.getNetworkConnection().getProductsData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Single<ResponseBody> sendDataToServer(String s){
        return RetrofitConnection.getNetworkConnection().sendDataToServer(s)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }



    public static void insertAllItems(final List<ProductModel>products,final List<CategoryModel>cats,
    final List<StockModel>stocks){
        final Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                getDao().insertAllProducts(products);
                getDao().insertAllCats(cats);
                getDao().insertAllStocks(stocks);
            }
        });
    }



    public static void insertAllOperations(final List<OperationsModel> operations){
        final Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                getDao().insertAlllOperations(operations);
            }
        });
    }


    public static  Observable<Long> insertItem(final ScanningItemModel item){
      return Observable.fromCallable(new Callable<Long>() {
          @Override
          public Long call() throws Exception {
             Long f= getDao().insertitem(item);
             return f;

          }
      }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    } public static  Observable<Integer> udateData(final List<ScanningItemModel> items){
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
               int updateItems= getDao().updateItems(items);
                return updateItems;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }


    public static  Observable<Integer> updateExportProduct(final ExportProductModel items){
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int updateItems= getDao().updateExportProduct(items);
                return updateItems;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }




    public static void deleteItem(final ScanningItemModel item){
        final Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                getDao().deleteItem(item);
            }
        });

    }
    public static LiveData<List<StockModel>>getAllStocks(){
        return getDao().getAllStocks();
    }

    public static LiveData<List<CategoryModel>>getAllCats(){
        return getDao().getAllCats();
    } public static LiveData<List<ProductModel>>getAllProducts(){
        return getDao().getAllProducts();
    }
    public static LiveData<Integer>getLastOperation(){
        return getDao().getLastOperation();
    }
    public static LiveData<List<Integer>>getAllOprations(){
        return getDao().getAllOprations();
    }

    public static LiveData<List<ExportProductModel>> getEXportProduct(int op_id) {
        return getDao().getEXportProduct(op_id,Constants.OPERATION_TYPE);
    }

    public static void insertAllExportsProducts(final List<ExportProductModel> exportProductModels) {

        final Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                getDao().insertAllExportsProducts(exportProductModels);

            }
        });
    }
}
