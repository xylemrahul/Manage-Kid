package com.example.milk.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierFragment extends Fragment {

    AutoCompleteTextView code, mrp, unit_price, selling_price,qty, total, paid, balance;
    TextView tx_final;
    Button saveBtn;
    Spinner spProduct;
    private ProgressDialog progressDialog;
    Type typeObj = null;

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

        View rootView = inflater.inflate(R.layout.fragment_supplier, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        code = view.findViewById(R.id.et_code);
        mrp = view.findViewById(R.id.et_mrp);
        unit_price = view.findViewById(R.id.et_unit_price);
        selling_price = view.findViewById(R.id.et_sp);
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
                List<Product> productList = response.body();
                showDefaultProduct(productList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

        code.setText(String.valueOf(typeObj.getInfo().getCode()));

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

    private void showDefaultProduct(final List<Product> productList) {

        final int defaultIndex = 0;

        List<String> productTitle = new ArrayList<>();
        productTitle.add(0, "Select Products");
        for (Product product : productList) {
            productTitle.add(product.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, productTitle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(adapter);
        spProduct.setSelection(defaultIndex);

        spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if(i > 0)
                {
                    tx_final.setVisibility(View.VISIBLE);
                    loadItemsAtPosition(i, productList);
                }else{
                    unit_price.getText().clear();
                    mrp.getText().clear();
                    qty.getText().clear();
                    selling_price.getText().clear();
                    total.getText().clear();
                    balance.getText().clear();

                    tx_final.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadItemsAtPosition(int i, List<Product> productList) {

        unit_price.setText(String.valueOf(productList.get(i-1).getUnitPrice()));
        mrp.setText(String.valueOf(productList.get(i-1).getMrp()));
        qty.setText(String.valueOf(productList.get(i-1).getQuantity()));
        selling_price.setText(String.valueOf(productList.get(i-1).getSellingPrice()));

        int final_total = Integer.parseInt(unit_price.getText().toString()) * Integer.parseInt(qty.getText().toString());
        int final_balance = final_total - Integer.valueOf(paid.getText().toString());

        total.setText(String.valueOf(final_total));
        balance.setText(String.valueOf(final_balance));

        tx_final.setText("Total : " +final_total+ "    " + "Paid : " + paid.getText().toString() + "    " + "Balance : " + final_balance );
    }

}
