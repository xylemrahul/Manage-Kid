package com.example.milk.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.milk.R;
import com.example.milk.activity.BarCodeActivity;
import com.example.milk.database.DatabaseClient;
import com.example.milk.model.Details;
import com.example.milk.model.Product;
import com.example.milk.model.Type;
import com.example.milk.retrofit.RetrofitAdapter;
import com.example.milk.retrofit.RetrofitService;
import com.example.milk.utils.AppUtilities;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Which id to best sent in case a tx_product is selected from dropdown. Do we send the same tx_product ID from latest tag in service 1 . In case of "Select Products" or"+" button,
//tx_product id sent is 0 .
public class SupplierFragment extends BaseFragment {

    EditText code, mrp, unit_price, selling_price,qty, total, paid, balance, tx_product;
    TextInputLayout inputProduct;
    TextView tx_final;
    Button saveBtn, addBtn;
    Spinner spProduct;
    private ProgressDialog progressDialog;
    Type typeObj = null;
    private boolean flag;
    int productId;

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
        tx_product = view.findViewById(R.id.et_product);
        inputProduct = view.findViewById(R.id.input_product);
        spProduct = view.findViewById(R.id.sp_product);
        saveBtn = view.findViewById(R.id.btnSave);
        addBtn = view.findViewById(R.id.btn_add);
        tx_final = view.findViewById(R.id.tx_final);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(isConnected) {
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
                    insert(productList);
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

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveDetails();
                }
            });

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addBtn.setVisibility(View.INVISIBLE);
                    spProduct.setVisibility(View.GONE);
                    inputProduct.setVisibility(View.VISIBLE);
                    productId = 0;
                }
            });
        }else{
            fetchAllProducts();
        }
    }

    private void fetchAllProducts() {

        new fetchProductsAsync(getActivity().getApplicationContext()).execute();
    }

    private void insert(final List<Product> productList) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase()
                        .productDAO()
                        .insertAll(productList);
            }
        }).start();
    }

    private class fetchProductsAsync extends AsyncTask<Void, Void, List<Product>> {
        List<Product> productList = new ArrayList<>();
        DatabaseClient instance = null;

        public fetchProductsAsync(Context applicationContext) {
          instance = DatabaseClient.getInstance(applicationContext);
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            productList = instance.getAppDatabase()
                    .productDAO()
                    .fetchProducts();

            return productList;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            showDefaultProduct(productList);
            Toast.makeText(getActivity(), getResources().getString(R.string.offline_save), Toast.LENGTH_SHORT).show();
        }
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
                    /*TODO*/
//                    productId = typeObj.getLatest().getProductId();
                    productId = 0;
                    loadItemsAtPosition(i, productList);
                }else{
                    productId = 0;
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
        int final_balance = 0;
        if(paid.getText().toString().length() > 0){
            final_balance = final_total - Integer.valueOf(paid.getText().toString());
        }
        total.setText(String.valueOf(final_total));
        balance.setText(String.valueOf(final_balance));
    }

    private void saveDetails(){
        int updated_balance = 0, paid_amount =0;
        if(paid.getText().toString().length() > 0) {
            paid_amount = Integer.valueOf(paid.getText().toString());
            updated_balance = Integer.valueOf(total.getText().toString()) - paid_amount;
        }
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HHmmss").format(Calendar.getInstance().getTime());
        String productTitle = tx_product.getText().toString();
        Details details = new Details(productTitle.length() > 0 ?productTitle : null, typeObj.getInfo().getCode(),Integer.parseInt(unit_price.getText().toString()), Integer.parseInt(total.getText().toString()),
                Integer.parseInt(qty.getText().toString()), 0, updated_balance,
                paid_amount ,null, timeStamp, Integer.parseInt(mrp.getText().toString()),Integer.valueOf(selling_price.getText().toString()) );

        if(isConnected) {
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
                    Toast.makeText(getActivity(), getResources().getString(R.string.success_save), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Details> call, Throwable t) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getActivity(), getResources().getString(R.string.error_msg_save), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            new insertDetailsAsync(getActivity().getApplicationContext(), details).execute();
        }
    }

    private class insertDetailsAsync extends AsyncTask<Void, Void, Void> {
        DatabaseClient instance = null;
        Details details = null;

        public insertDetailsAsync(Context applicationContext, Details details) {
            instance = DatabaseClient.getInstance(applicationContext);
            this.details = details;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            instance.getAppDatabase()
                    .detailsDAO()
                    .insert(details);

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getActivity(), getResources().getString(R.string.offline_save), Toast.LENGTH_SHORT).show();
        }
    }


    private void loadDetails(Details body){
        int final_total = body.getTotal();
        int final_paid = body.getPaid();

        int final_balance = final_total - final_paid;

        balance.setText(String.valueOf(final_balance));
        total.setText(String.valueOf(final_total));
        paid.setText(String.valueOf(final_paid));

        tx_final.setText("Total : " +final_total+ "    " + "Paid : " + final_paid + "    " + "Balance : " + final_balance );

        Intent intent = new Intent(getActivity(), BarCodeActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        getActivity().finish();
    }

}
