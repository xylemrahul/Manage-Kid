package com.example.milk.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.milk.model.Type;

@Dao
public interface TypeDAO {

    @Query("select * from TYPE")
    Type fetch();
}
