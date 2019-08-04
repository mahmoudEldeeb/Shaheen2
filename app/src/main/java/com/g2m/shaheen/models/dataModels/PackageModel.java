package com.g2m.shaheen.models.dataModels;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.g2m.shaheen.utils.Constants;

@Entity(tableName = Constants.PACKAGE_TABLE)

public class PackageModel {
    @PrimaryKey
    @NonNull
    public String barcodee;
    public String package_name;

}
