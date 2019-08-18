package com.example.milk.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.milk.model.Details;

@Dao
public interface DetailsDAO {

    @Insert
    void insert(Details details);

    @Query("select * from DETAILS")
    Details fetchDetails();
}
