package com.g2m.shaheen.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.g2m.shaheen.models.Repositry;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ScanningItemViewModel extends ViewModel {

    public MutableLiveData<Long> saveItem(ScanningItemModel scanningItemModel){
        final MutableLiveData<Long> idResult=new MutableLiveData<>();
 Repositry.insertItem(scanningItemModel).safeSubscribe(new Observer<Long>() {
     @Override
     public void onSubscribe(Disposable d) {

     }

     @Override
     public void onNext(Long aLong) {
            idResult.setValue(aLong); ;
     }

     @Override
     public void onError(Throwable e) {

     }

     @Override
     public void onComplete() {

     }
 });
return idResult;
   }

    public void deleteItem(ScanningItemModel scanningItemModel){

        Repositry.deleteItem(scanningItemModel);
    }

    public MutableLiveData<Integer>updateExportProduct(ExportProductModel productModel){
        final MutableLiveData<Integer>results=new MutableLiveData<>();
        Repositry.updateExportProduct(productModel).safeSubscribe(new Observer<Integer>() {
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
