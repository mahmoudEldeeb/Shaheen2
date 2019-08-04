package com.g2m.shaheen.models.dataModels;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.g2m.shaheen.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = Constants.EXPORT_PRODUCTS_TABLE,primaryKeys={"id","type","op_id"})
public class ExportProductModel implements Serializable {

    public int id;
    public int op_id;
    public String name;
    public int quantity;
    public int  quantitiy_recivved;
    public int type;

    public ExportProductModel(int id,int op_id, String name, int quantity,int type) {
        this.id=id;
        this.op_id = op_id;
        this.name = name;
        this.quantity = quantity;
        this.type=type;

    }

    public void setQuantitiy_recivved(int quantitiy_recivved) {
        this.quantitiy_recivved = quantitiy_recivved;
    }
}
