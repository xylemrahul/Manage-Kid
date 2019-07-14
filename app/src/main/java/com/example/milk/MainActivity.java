package com.example.milk;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.milk.fragments.CustomerFragment;
import com.example.milk.fragments.NewUserFragment;
import com.example.milk.model.Type;
import com.example.milk.retrofit.RetrofitAdapter;
import com.example.milk.retrofit.RetrofitService;
import com.example.milk.utils.AppUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FrameLayout mContainer;
    private ProgressDialog progressDialog;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);

        final String barcodeValue = getIntent().getStringExtra(AppUtilities.barcode_value);

        mContainer = findViewById(R.id.container);

        RetrofitService retrofitService = RetrofitAdapter.create();
        Call<Type> infoCall = retrofitService.listRepos(8982922);

        progressDialog.setMessage("Fetching details...");
        progressDialog.show();

        infoCall.enqueue(new Callback<Type>() {
            @Override
            public void onResponse(Call<Type> call, Response<Type> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Bundle bundle = new Bundle();
                bundle.putString(AppUtilities.barcode_value, barcodeValue);

                if(response.body() == null){

                    Fragment newFragment = new NewUserFragment();
                    newFragment.setArguments(bundle);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, newFragment);
                    transaction.commit();
                }else {

                    Fragment newFragment = new CustomerFragment();
                    bundle.putParcelable(AppUtilities.type_obj,response.body());
                    newFragment.setArguments(bundle);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, newFragment);
                    transaction.commit();
                }
            }

            @Override
            public void onFailure(Call<Type> call, Throwable t) {

                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }
}