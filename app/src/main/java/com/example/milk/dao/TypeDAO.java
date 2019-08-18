package com.example.milk.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.milk.model.Type;

@Dao
public interface TypeDAO {

    @Insert
    void insert(Type type);

    @Query("select * from TYPE")
    Type fetch();
}
