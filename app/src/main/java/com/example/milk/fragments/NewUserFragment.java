package com.example.milk.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.milk.R;
import com.example.milk.database.DatabaseClient;
import com.example.milk.model.Info;
import com.example.milk.model.Type;
import com.example.milk.retrofit.RetrofitAdapter;
import com.example.milk.retrofit.RetrofitService;
import com.example.milk.utils.AppUtilities;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUserFragment extends BaseFragment {

    EditText code,pName, mobile, address;
    private TextInputLayout nameLayout, mobileLayout, addressLayout;
    Button saveBtn;
    Spinner spType;
    Long barcode;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            barcode = getArguments().getLong(AppUtilities.barcode_value);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_user, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());

        code = view.findViewById(R.id.et_code);
        code.setText(""+barcode);

        spType = view.findViewById(R.id.sp_type);
        pName = view.findViewById(R.id.et_name);
        mobile = view.findViewById(R.id.et_mobile);
        address = view.findViewById(R.id.et_address);
        saveBtn = view.findViewById(R.id.btnSave);

        nameLayout = view.findViewById(R.id.ip_name);
        mobileLayout = view.findViewById(R.id.ip_mobile);
        addressLayout = view.findViewById(R.id.ip_address);

        pName.addTextChangedListener(new MyTextWatcher(nameLayout));
        mobile.addTextChangedListener(new MyTextWatcher(mobileLayout));
        address.addTextChangedListener(new MyTextWatcher(addressLayout));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(adapter);

        final String type = spType.getSelectedItem().toString();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = pName.getText().toString();
                String phn = mobile.getText().toString();
                String add = address.getText().toString();

                if (!validateName()) {
                    return;
                }

                if (!validateMobile()) {
                    return;
                }

                if (!validateAddress()) {
                    return;
                }
               final Info info = new Info(barcode, name, phn, add);

                if(isConnected) {

                    RetrofitService retrofitService = RetrofitAdapter.create();
                    Call<Type> register = null;

                    if (type.equalsIgnoreCase("customer")) {
                        register = retrofitService.saveCustomer(info);
                    } else {
                        register = retrofitService.saveSupplier(info);
                    }
                    progressDialog.setMessage("Saving details...");
                    progressDialog.show();
                    register.enqueue(new Callback<Type>() {
                        @Override
                        public void onResponse(Call<Type> call, Response<Type> response) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (type.equals("customer")) {
                            }
                            Toast.makeText(getActivity(), "Details Saved Successfully", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<Type> call, Throwable t) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(getActivity(), getResources().getString(R.string.error_msg_save), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                   insert(info);
                }
            }
        });
    }

    private void insert(final Info info){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase()
                        .infoDAO()
                        .insert(info);
            }
        }).start();
    }

    private boolean validateName() {
        if (pName.getText().toString().trim().isEmpty()) {
            nameLayout.setError(getString(R.string.err_name));
            requestFocus(pName);
            return false;
        } else {
            nameLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateMobile() {

        if (mobile.getText().toString().trim().isEmpty() || mobile.getText().toString().trim().length() < 10) {
            mobileLayout.setError(getString(R.string.err_mobile));
            requestFocus(mobile);
            return false;
        } else {
            mobileLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress() {
        if (address.getText().toString().trim().isEmpty()) {
            addressLayout.setError(getString(R.string.err_address));
            requestFocus(address);
            return false;
        } else {
            addressLayout.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.ip_name:
                    validateName();
                    break;
                case R.id.ip_mobile:
                    validateMobile();
                    break;
                case R.id.ip_address:
                    validateAddress();
                    break;
            }
        }
    }
}
