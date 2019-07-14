package com.example.milk.retrofit;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {

    private String credentials;

    public CustomInterceptor(String username, String password) {
        this.credentials = username + ":" + password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();

        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//        String credentialsFinal = "Basic" + "btoa(" + credentials + ")";
        Request request = originalRequest.newBuilder()
                .header("Authorization", basic)
                .header("Content-Type", "application/json")
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(request);
    }
}
