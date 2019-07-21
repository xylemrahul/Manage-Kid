package com.example.milk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView email, password;
    private TextView forgotPass, signUp;
    private Button btnSignIn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeGUI();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inEmail = email.getText().toString();
                String inPassword = password.getText().toString();

                if (validateInput(inEmail, inPassword)) {
                    signUser(inEmail, inPassword);
                }

            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
             }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

    private void initializeGUI() {

        email = findViewById(R.id.atvEmailLog);
        password = findViewById(R.id.atvPasswordLog);
        forgotPass = findViewById(R.id.tvForgotPass);
        signUp = findViewById(R.id.tvSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressDialog = new ProgressDialog(this);
    }

    public void signUser(String email, String password) {

        progressDialog.setMessage("Verifying...");
//        progressDialog.show();
        startActivity(new Intent(LoginActivity.this, BarCodeActivity.class));
        finish();
    }

    public boolean validateInput(String inemail, String inpassword) {

        if (inemail.isEmpty()) {
            email.setError("Email field is empty.");
            return false;
        }
        if (inpassword.isEmpty()) {
            password.setError("Password is empty.");
            return false;
        }

        return true;
    }
}
