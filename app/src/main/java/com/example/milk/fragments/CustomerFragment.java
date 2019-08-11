package com.example.milk.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.milk.R;
import com.example.milk.model.Details;
import com.example.milk.model.Product;
import com.example.milk.model.Type;
import com.example.milk.retrofit.RetrofitAdapter;
import com.example.milk.retrofit.RetrofitService;
import com.example.milk.utils.AppUtilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    int mrp;

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
                List<Product> productList = response.body();
                showDefaultProduct(productList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                Toast.makeText(getActivity(), getResources().getString(R.string.error_msg_fetch), Toast.LENGTH_SHORT).show();
            }
        });

        code.setText(String.valueOf(typeObj.getInfo().getCode()));
        prev_unit_price.setText(String.valueOf(typeObj.getLatest().getUnitPrice()));
        qty.setText(String.valueOf(typeObj.getLatest().getQuantity()));

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });
    }

    private void showDefaultProduct(List<Product> productList) {

        int defaultIndex = 0;

        List<String> productTitle = new ArrayList<>();
        for (Product product : productList) {

            productTitle.add(product.getTitle());
            if(typeObj.getLatest().getId() == product.getId()){
                defaultIndex = productList.indexOf(product);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, productTitle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(adapter);
        spProduct.setSelection(defaultIndex);

        unit_price.setText(String.valueOf(productList.get(defaultIndex).getSellingPrice()));
        mrp = productList.get(defaultIndex).getMrp();

        int final_total = Integer.parseInt(unit_price.getText().toString()) * Integer.parseInt(qty.getText().toString());
        int final_balance = 0;
        if(paid.getText().toString().length() > 0){
            final_balance = final_total - Integer.valueOf(paid.getText().toString());
        }

        total.setText(String.valueOf(final_total));
        balance.setText(String.valueOf(final_balance));
    }

    private void saveDetails(){
        int updated_balance = 0, paid_amount = 0;
        if(paid.getText().toString().length() > 0) {
            paid_amount = Integer.valueOf(paid.getText().toString());
            updated_balance = Integer.valueOf(total.getText().toString()) - paid_amount;
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HHmmss").format(Calendar.getInstance().getTime());
        Details details = new Details(null, typeObj.getInfo().getCode(),typeObj.getLatest().getUnitPrice(), Integer.parseInt(total.getText().toString()),
                typeObj.getLatest().getQuantity(), typeObj.getLatest().getProductId(), updated_balance,
                paid_amount ,null, timeStamp, mrp,Integer.valueOf(unit_price.getText().toString()) );

        RetrofitService retrofitService = RetrofitAdapter.create();
        Call<Details> detailsCall = retrofitService.saveIncomming(details);

        progressDialog.setMessage("Saving data...");
        progressDialog.show();

        detailsCall.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                loadDetails(response.body());
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                Toast.makeText(getActivity(), getResources().getString(R.string.error_msg_save), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDetails(Details body){
        int final_total = body.getTotal();
        int final_paid = body.getPaid();

        int final_balance = final_total - final_paid;

        balance.setText(String.valueOf(final_balance));
        total.setText(String.valueOf(final_total));
        paid.setText(String.valueOf(final_paid));

        tx_final.setText("Total : " +final_total+ "    " + "Paid : " + final_paid + "    " + "Balance : " + final_balance );
    }
}
