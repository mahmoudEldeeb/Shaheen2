package com.g2m.shaheen.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.g2m.shaheen.models.Repositry;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.models.dataModels.Result;
import com.g2m.shaheen.models.repositries.NetworkRepositry;
import com.g2m.shaheen.utils.Constants;
import com.g2m.shaheen.utils.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class ProductViewModel extends ViewModel {

public MutableLiveData<List<ExportProductModel>>getProductsOfOperations(final int order_id){
      final MutableLiveData<List<ExportProductModel>>result=new MutableLiveData<>();
    final List<ExportProductModel>list1=new ArrayList<>();
    NetworkRepositry.getProductsOfOperations(order_id).subscribeWith(new SingleObserver<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(ResponseBody responseBody) {

            try {
                String s=responseBody.string();
                Log.v("iiii",s);
                JSONObject jsonObject=new JSONObject(s);
                JSONArray stock_out_details;
                if(Constants.OPERATION_TYPE==6)
                    stock_out_details =jsonObject.getJSONArray("stock_adjust_details");
              else   stock_out_details=jsonObject.getJSONArray("stock_out_details");

                for(int j=0;j<stock_out_details.length();j++){
                    JSONObject object=stock_out_details.getJSONObject(j);
                    JSONArray ids=object.getJSONArray("product_id");
                    int id=ids.getInt(0);
                    String name=ids.getString(1);
                    int qty;
                    if(Constants.OPERATION_TYPE==6)
                    {
                    qty =object.getInt("theoretical_qty");
                    }
                    else {
                        qty =object.getInt("product_qty");
                    }
                    ExportProductModel productModel=new ExportProductModel(id,order_id,name
                            ,qty,Constants.OPERATION_TYPE);
                    Log.v("iiiiiiioooooo",id+"  vvvvvvvv");
                    list1.add(productModel);
                }
            } catch (JSONException e) {
                Log.v("iiiiiiio",e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.v("iiiiiiiooo",e.getMessage());
                e.printStackTrace();
            }
            result.setValue(list1);
        }

        @Override
        public void onError(Throwable e) {
            Helper.dismiss();
            Helper.showDialog("لايمكن ان تتم العملية ",e.getMessage());
        }
    });
    return result;
}


public void sendDataToServer(String data,int op_id){
    Helper.showDialog();
NetworkRepositry.stock_out_confirm(data,op_id).subscribeWith(new SingleObserver<Result>() {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(Result result) {

            if(result.success.equals("1")){
                Helper.showDialog("تمت العملية",result.message);
            }
            else Helper.showDialog("تمت العملية",result.message);
         Helper.dismiss();
    }

    @Override
    public void onError(Throwable e) {
        Helper.dismiss();
    }
});

}
    public LiveData<List<ExportProductModel>> getOfflineProducts(int op_id){
        return Repositry.getEXportProduct(op_id);
    }
    public void insertAllExportsProducts(List<ExportProductModel>exportProductModels){
        Repositry.insertAllExportsProducts(exportProductModels);
    }


}
