package com.g2m.shaheen.models.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.ExportProductModel;
import com.g2m.shaheen.models.dataModels.OperationsModel;
import com.g2m.shaheen.models.dataModels.PackageModel;

import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.ScanningItemModel;
import com.g2m.shaheen.models.dataModels.StockModel;
import com.g2m.shaheen.utils.Constants;


@Database(entities = {StockModel.class, ProductModel.class
        , ExportProductModel.class,
        ScanningItemModel.class, CategoryModel.class, PackageModel.class, OperationsModel.class},version = 1, exportSchema = true)

public abstract class LocalDatabase extends RoomDatabase {
    public abstract DataDao dao();
    private static volatile LocalDatabase INSTANCE;
    public static LocalDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Constants.context.getApplicationContext(),
                            LocalDatabase.class, Constants.DATABASE_NAME)
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
}
