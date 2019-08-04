package com.g2m.shaheen.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.g2m.shaheen.models.Repositry;
import com.g2m.shaheen.models.dataModels.Result;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.utils.Helper;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class OperationDetailsViewModdel extends ViewModel {
    public LiveData<List<ScanningItemModel>> getScanningItems(int id){
        return Repositry.getScanningItems(id);
    }

}
