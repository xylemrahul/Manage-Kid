package com.example.milk.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.milk.R;
import com.example.milk.model.Product;
import com.example.milk.model.Type;
import com.example.milk.retrofit.RetrofitAdapter;
import com.example.milk.retrofit.RetrofitService;
import com.example.milk.utils.AppUtilities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerFragment extends Fragment {

    EditText code, prev_unit_price, unit_price, qty, total, paid, balance;
    TextView tx_final;
    Button saveBtn;
    Spinner spProduct;
    private ProgressDialog progressDialog;
    Type typeObj = null;
    List<Product> productList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            typeObj = getArguments().getParcelable(AppUtilities.type_obj);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_customer, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        code = view.findViewById(R.id.et_code);
        prev_unit_price = view.findViewById(R.id.et_prev_unit_price);
        unit_price = view.findViewById(R.id.et_unit_price);
        qty = view.findViewById(R.id.et_qty);
        total = view.findViewById(R.id.et_total);
        paid = view.findViewById(R.id.et_paid);
        balance = view.findViewById(R.id.et_balance);
        spProduct = view.findViewById(R.id.sp_product);
        saveBtn = view.findViewById(R.id.btnSave);
        tx_final = view.findViewById(R.id.tx_final);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RetrofitService retrofitService = RetrofitAdapter.create();
        Call<List<Product>> fetchProducts = retrofitService.getProducts();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        fetchProducts.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                productList = response.body();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

        code.setText(typeObj.getInfo().getCode());
        prev_unit_price.setText(typeObj.getLatest().getUnitPrice());
        qty.setText(typeObj.getLatest().getQuantity());

        List<String> productTitle = new ArrayList<>();
        for (Product product : productList) {
            if(typeObj.getLatest().getId() == product.getId()){
                
            }
            productTitle.add(product.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, productTitle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(adapter);




        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String name = pName.getText().toString();
//                String phn = mobile.getText().toString();
//                String add = address.getText().toString();

//                if (validateInput(name, phn, add)) {
//                    Toast.makeText(MainActivity.this, "Saved to Db", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public boolean validateInput(String name, String phn, String add) {

        if (name.isEmpty()) {
//            pName.setError("Email field is empty.");
            return false;
        }
        if (phn.isEmpty()) {
//            mobile.setError("Password is empty.");
            return false;
        }

        if (add.isEmpty()) {
//            address.setError("Password is empty.");
            return false;
        }
        return true;
    }
}
