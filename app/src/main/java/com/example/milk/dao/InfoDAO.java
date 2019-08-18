package com.example.milk.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.milk.model.Info;

@Dao
public interface InfoDAO {

    @Insert
    public void insert(Info info);

    @Query("Select * from INFO")
    public Info fetch();
}
