package com.g2m.shaheen.models.dataModels.ResultModel;

import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OperationsResult implements Serializable {
    @SerializedName(value="stock_out", alternate="stock_adjust")
    List<OperationsModel>stock_out;

    public List<OperationsModel> getStock_out() {
        return stock_out;
    }

    public void setStock_out(List<OperationsModel> stock_out) {
        this.stock_out = stock_out;
    }
}
