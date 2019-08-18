package com.example.milk.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.milk.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert
    void insert(Product product);

    @Query("select * from PRODUCT")
    List<Product> fetchProducts();
}
