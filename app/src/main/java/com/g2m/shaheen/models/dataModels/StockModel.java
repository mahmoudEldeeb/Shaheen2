package com.g2m.shaheen.models.dataModels;



import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.g2m.shaheen.utils.Constants;

@Entity(tableName = Constants.STORE_TABLE)
public class StockModel {
    @PrimaryKey
    @NonNull
    public int id;
    public String name;
}
