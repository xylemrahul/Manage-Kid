package com.example.milk.utils;

import androidx.room.TypeConverter;

import com.example.milk.model.Info;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class InfoConverter {

    @TypeConverter
    public static Info fromString(String value) {
        Type listType = new TypeToken<Info>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromInfo(Info info) {
        Gson gson = new Gson();
        String json = gson.toJson(info);
        return json;
    }
}
