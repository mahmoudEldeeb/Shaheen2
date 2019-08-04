package com.g2m.shaheen.models.network;

import com.g2m.shaheen.models.dataModels.Result;
import com.g2m.shaheen.models.dataModels.ResultModel.OperationsResult;
import com.g2m.shaheen.models.dataModels.ResultModel.ProductDataResult;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkApi {


    @GET("client.php")
    Single<ResponseBody> test();

    @POST ("stock_in_end_last.php")
    @FormUrlEncoded
    Single<ResponseBody> sendDataToServer(@Field("data") String data);

    @GET("product.php")
    Single<ProductDataResult> getProductsData();


    @POST("stock_out_get.php")
    @FormUrlEncoded
    Single<OperationsResult> getOperation(@Field("type")int type);


    @GET("stock_adjust.php")
    Single<OperationsResult> getOperationAdjust();

    @POST("stock_adjust_details.php")
    @FormUrlEncoded
    Single<ResponseBody> getProductsOfAdjustOperations(@Field("order_id")int order_id);

    @POST("stock_out_details.php")
    @FormUrlEncoded
    Single<ResponseBody> getProductsOfOperations(@Field("order_id")int order_id);

    @POST("stock_out_confirm.php")
    @FormUrlEncoded
    Single<Result> stock_out_confirm(@Field("data")String data, @Field("order_id")int order_id);


    @POST("stock_adjust_confirm.php")
    @FormUrlEncoded
    Single<Result> stock_adjust_confirm(@Field("data")String data,@Field("order_id")int order_id);






}