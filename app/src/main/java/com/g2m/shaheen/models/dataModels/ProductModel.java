package com.g2m.shaheen.models.dataModels;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.g2m.shaheen.utils.Constants;

@Entity(tableName = Constants.PRODUCTS_TABLE)
public class ProductModel {
    @PrimaryKey
    @NonNull
    public String barcode;
    public String name;
    public int id;


}
