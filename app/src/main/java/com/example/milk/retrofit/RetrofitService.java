package com.example.milk.retrofit;

import com.example.milk.model.Details;
import com.example.milk.model.Info;
import com.example.milk.model.Product;
import com.example.milk.model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("getInfo/{code}")
    Call<Type> listRepos(@Path("code") int code);

    @POST("saveDairyCustomers")
    Call<Type> saveCustomer(@Body Info info);

    @POST("saveSupplier")
    Call<Type> saveSupplier(@Body Info info);

    @GET("getProducts")
    Call<List<Product>> getProducts();

    @POST("incomming")
    Call<Details> saveIncomming(@Body Details details );
}
