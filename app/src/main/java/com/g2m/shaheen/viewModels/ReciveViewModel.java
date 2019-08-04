package com.g2m.shaheen.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.g2m.shaheen.models.Repositry;
import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.Result;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.models.dataModels.StockModel;
import com.g2m.shaheen.utils.Helper;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class ReciveViewModel extends ViewModel {

    public LiveData<List<StockModel>> getAllStocks(){
        return Repositry.getAllStocks();
    }
    public LiveData<List<CategoryModel>>getAllCats(){
        return Repositry.getAllCats();
    }
    public LiveData<List<ProductModel>>getAllProducts(){
        return Repositry.getAllProducts();
    }
    public LiveData<List<ScanningItemModel>>getScanningItems(int id){
        return Repositry.getScanningItems(id);
    }


    public LiveData<Integer> getItemCount(String barcode, int operationNumber){

        return Repositry.getItemCount(barcode,operationNumber);
    }
    public MutableLiveData<Integer> sendDataToServer(String s){
        Helper.showDialog();
        final MutableLiveData<Integer>res=new MutableLiveData<>();
        Repositry.sendDataToServer(s).subscribeWith(new SingleObserver<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ResponseBody result) {
                    Helper.dismiss();
                try {
                    Log.v("mmm",result.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            /*    if(responseBody.success.equals("1")){
                    Helper.showDialog(" تمة العملية ","تم الارسال");
                }
                else    Helper.showDialog("لم تمة العملية ",responseBody.message);
                        Log.v("mmmm",responseBody.id+"");
                        */
//                        if(result.success.equals("1")){
//                            res.setValue(1);
//                            Helper.showDialog("تم العملية ",result.message);
//                        }
//                    else {
//                        res.setValue(0);
//                            Helper.showDialog("تم العملية ",result.message);
//                        }

            }
            @Override
            public void onError(Throwable e) {
                res.setValue(0);
                Log.v("ssssd222",e.getMessage());
                Helper.showDialog("لم تتم العملية ",e.getMessage());
                Helper.dismiss();
            }
        });
    return res;
    }
public MutableLiveData<Integer>updateData(List<ScanningItemModel>list){
    final MutableLiveData<Integer>results=new MutableLiveData<>();
        Repositry.udateData(list).safeSubscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer longs) {
                results.setValue(longs);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return results;
}
}
