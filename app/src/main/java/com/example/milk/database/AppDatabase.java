package com.example.milk.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.milk.dao.DetailsDAO;
import com.example.milk.dao.InfoDAO;
import com.example.milk.dao.ProductDAO;
import com.example.milk.dao.TypeDAO;
import com.example.milk.model.Details;
import com.example.milk.model.Info;
import com.example.milk.model.Product;
import com.example.milk.model.Type;
import com.example.milk.utils.InfoConverter;
import com.example.milk.utils.LatestConverter;

@Database(entities = {Details.class, Product.class, Type.class, Info.class}, version = 1, exportSchema = false)
@TypeConverters({InfoConverter.class, LatestConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract DetailsDAO detailsDAO();
    public abstract ProductDAO productDAO();
    public abstract TypeDAO typeDAO();
    public abstract InfoDAO infoDAO();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
