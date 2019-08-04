package com.g2m.shaheen.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.g2m.shaheen.models.Repositry;

import java.util.List;

public class OperationViewModel extends ViewModel {
public LiveData<List<Integer>> getAllOprations(){
   return Repositry.getAllOprations();
}
}
