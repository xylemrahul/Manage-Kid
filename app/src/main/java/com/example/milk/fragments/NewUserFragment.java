package com.example.milk.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.milk.model.Info;
import com.example.milk.model.Type;
import com.example.milk.retrofit.RetrofitAdapter;
import com.example.milk.retrofit.RetrofitService;
import com.example.milk.utils.AppUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUserFragment extends Fragment {

    AutoCompleteTextView pName, mobile, address, code;
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

                if (validateInput(name, phn, add)) {
                    Info info = new Info(barcode, name, phn, add);

                    RetrofitService retrofitService = RetrofitAdapter.create();
                    Call<Type> register = null;
                    if (type.equals("customer")) {
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
                        }

                        @Override
                        public void onFailure(Call<Type> call, Throwable t) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(getActivity(), getResources().getString(R.string.error_msg_save), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public boolean validateInput(String name, String phn, String add) {

        if (name.isEmpty()) {
            pName.setError("Name field is empty");
            return false;
        }
        if (phn.isEmpty() || phn.length() < 10) {
            mobile.setError("Enter valid mobile number");
            return false;
        }

        if (add.isEmpty()) {
            address.setError("Address field is empty.");
            return false;
        }
        return true;
    }
}
