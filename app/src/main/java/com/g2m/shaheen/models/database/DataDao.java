package com.g2m.shaheen.models.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.models.dataModels.StockModel;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT COUNT() FROM PACKAGE_TABLE WHERE barcodee = :id")
    LiveData<Integer> isExist(String id);

    @Query("SELECT COUNT() FROM items_table WHERE packageBarcode = :code And operationId=:operationNumber")
    LiveData<Integer> getItemCount(String code, int operationNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllProducts(List<ProductModel> ProductModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCats(List<CategoryModel> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllStocks(List<StockModel> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlllOperations(List<OperationsModel> items);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllExportsProducts(List<ExportProductModel> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertitem(ScanningItemModel items);

    @Delete
    void deleteItem(ScanningItemModel items);

    @Query("DELETE  FROM ITEMS_TABLE where barcode=:barcode And packageBarcode=:packageBarcode And stockId=:stockId And " +
            "catId=:catId And productId=:productId And operationId=:operationId")
    void deleteSpecificItem(String barcode, String packageBarcode, int stockId, int catId,
                            int productId, int operationId);


    @Query("SELECT * FROM store_table")
    LiveData<List<StockModel>> getAllStocks();

    @Query("SELECT * FROM OPERATIONS_TABLE where type=:type")
    LiveData<List<OperationsModel>> getEXportOperations(int type);

    @Query("SELECT * FROM EXPORT_PRODUCTS_TABLE where op_id=:id  And type=:type")
    LiveData<List<ExportProductModel>> getEXportProduct(int id,int type);



    @Query("SELECT * FROM category_table")
    LiveData<List<CategoryModel>> getAllCats();

    @Query("SELECT * FROM products_table")
    LiveData<List<ProductModel>> getAllProducts();

    /* @Query("SELECT * FROM ITEMS_TABLE where id=:barcode")
    LiveData<ProductModel> getBarcodeData(String barcode);
/*/
    @Query("SELECT * FROM ITEMS_TABLE where operationId=:op_id")
    LiveData<List<ScanningItemModel>> getScanningItems(int op_id);

    @Query("SELECT Max(operationId) FROM ITEMS_TABLE ")
    LiveData<Integer> getLastOperation();

    @Query("SELECT DISTINCT operationId FROM ITEMS_TABLE ")
    LiveData<List<Integer>> getAllOprations();
    @Update
    int updateItems(List<ScanningItemModel>items);

    @Update
    int updateExportProduct(ExportProductModel model);

}
