package com.example.milk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milk.R;


public class ForgotPasswordActivity extends BaseActivity {

    private ImageView ivPWreset;
    private TextView tvInfo, tvSignin;
    private AutoCompleteTextView atvEmail;
    private Button btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwreset);
        initializeGUI();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = atvEmail.getText().toString();

                if (email.isEmpty()) {
                    atvEmail.setError("Please, fill the email field.", null);
                } else {
                    startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                    finish();
//                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(ForgotPasswordActivity.this, "Email has been sent successfully.", Toast.LENGTH_SHORT).show();
//                                finish();
//                                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
//                            } else {
//                                Toast.makeText(ForgotPasswordActivity.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });

                }

            }
        });


        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                finish();
            }
        });


    }




    private void initializeGUI() {
        ivPWreset = findViewById(R.id.ivPassReset);
        tvInfo = findViewById(R.id.tvPWinfo);
        tvSignin = findViewById(R.id.tvGoBack);
        atvEmail = findViewById(R.id.atvEmailRes);
        btnReset = findViewById(R.id.btnReset);

//        firebaseAuth = FirebaseAuth.getInstance();

    }
}
