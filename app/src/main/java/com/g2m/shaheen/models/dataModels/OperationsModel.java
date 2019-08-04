package com.g2m.shaheen.models.dataModels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.g2m.shaheen.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = Constants.OPERATIONS_TABLE,primaryKeys={"id","type"})
public class OperationsModel implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName(value="min_date", alternate="date")
    public String min_date;
    @SerializedName("type")
    public int type;

    public OperationsModel(int id, String name, String min_date) {
        this.id = id;
        this.name = name;
        this.min_date = min_date;
        type = Constants.OPERATION_TYPE;
        Log.v("aaaaa",type+"   b");
    }

    public void setType(int type) {
        this.type = type;
    }
}
