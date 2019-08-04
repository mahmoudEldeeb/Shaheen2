package com.g2m.shaheen.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.g2m.shaheen.models.Repositry;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.models.dataModels.ResultModel.OperationsResult;
import com.g2m.shaheen.models.repositries.NetworkRepositry;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ExportViewModel extends AndroidViewModel {
    public ExportViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<OperationsResult>getoperations(){
        final MutableLiveData<OperationsResult>resultMutableLiveData=new MutableLiveData<>();
        NetworkRepositry.getOperation().subscribeWith(new SingleObserver<OperationsResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onSuccess(OperationsResult operationsResult) {
                Log.v("qqqq","v    "+Constants.OPERATION_TYPE);
                for(int i=0;i<operationsResult.getStock_out().size();i++){
                    operationsResult.getStock_out().get(i).setType(Constants.OPERATION_TYPE);
                }
                resultMutableLiveData.setValue(operationsResult);


            }

            @Override
            public void onError(Throwable e) {
                Helper.dismiss();
                Helper.showDialog("فشل العملية",e.getMessage());
            }
        });
        return resultMutableLiveData;
    }
    public LiveData<List<OperationsModel>>getOfflineOperation(){
        return Repositry.getEXportOperations();
    }
    public void insertAlloexportOperations(List<OperationsModel>operationsModels){

        Repositry.insertAllOperations(operationsModels);
    }
}
