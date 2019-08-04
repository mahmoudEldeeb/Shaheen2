package com.g2m.shaheen.viewModels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.g2m.shaheen.models.Repositry;

public class ProcessViewModel extends ViewModel {
    public LiveData<Integer> getLastOperation(){
       return Repositry.getLastOperation();
    }

}
