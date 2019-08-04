package com.g2m.shaheen.models.repositries;

import com.g2m.shaheen.models.dataModels.Result;
import com.g2m.shaheen.models.dataModels.ResultModel.OperationsResult;
import com.g2m.shaheen.models.network.RetrofitConnection;
import com.g2m.shaheen.utils.Constants;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class NetworkRepositry {
    public static Single<OperationsResult> getOperation(){
        if(Constants.OPERATION_TYPE==6){
            return RetrofitConnection.getNetworkConnection().getOperationAdjust()
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        }
      else{
          return RetrofitConnection.getNetworkConnection().getOperation(Constants.OPERATION_TYPE)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }
    public static Single<ResponseBody> getProductsOfOperations(int order_id){
        if(Constants.OPERATION_TYPE==6) {
            return RetrofitConnection.getNetworkConnection().getProductsOfAdjustOperations(order_id)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
        else {

            return RetrofitConnection.getNetworkConnection().getProductsOfOperations(order_id)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }

    public static Single<Result> stock_out_confirm(String data, int order_id){
        if(Constants.OPERATION_TYPE==6){

            return RetrofitConnection.getNetworkConnection().stock_adjust_confirm(data, order_id)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
        else {
            return RetrofitConnection.getNetworkConnection().stock_out_confirm(data, order_id)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }

}
