package com.example.milk.utils;

import androidx.room.TypeConverter;

import com.example.milk.model.Latest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LatestConverter {

    @TypeConverter
    public static Latest fromString(String value) {
        Type listType = new TypeToken<Latest>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromLatest(Latest latest) {
        Gson gson = new Gson();
        String json = gson.toJson(latest);
        return json;
    }
}
