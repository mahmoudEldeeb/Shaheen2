package com.g2m.shaheen.models.dataModels;



import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.g2m.shaheen.utils.Constants;

@Entity(tableName = Constants.ITEMS_TABLE)
public class ScanningItemModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String barcode;
    public int stockId;
    public int catId;
    public int productId;
    public int operationId;
    public String packageBarcode;
    public String stock_name;
    public String cat_name;
    public String product_name;
    public boolean senOrNot=false;

    @Ignore
public ScanningItemModel(){

}
    public ScanningItemModel(String barcode, String packageBarcode, int stockId, int catId,
                             int productId, int operationId, String stock_name,
                                      String cat_name,
                                      String product_name)
    {
        this.barcode = barcode;
        this.stockId = stockId;
        this.catId = catId;
        this.productId = productId;
        this.operationId = operationId;
        this.packageBarcode=packageBarcode;
        this.stock_name=stock_name;
        this.cat_name=cat_name;
        this.product_name=product_name;
    }

}
