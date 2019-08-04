package com.g2m.shaheen.models.dataModels;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.g2m.shaheen.utils.Constants;


@Entity(tableName = Constants.CATEGORY_TABLE)
public class CategoryModel {
    @PrimaryKey
    @NonNull
    public int id;
    public String name;

}
