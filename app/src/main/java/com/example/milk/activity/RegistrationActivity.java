
package com.example.milk.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milk.R;


public class RegistrationActivity extends BaseActivity {

    private ImageView logo, joinus;
    private AutoCompleteTextView username, email, password;
    private Button signup;
    private TextView signin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initializeGUI();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String inputName = username.getText().toString().trim();
                final String inputPw = password.getText().toString().trim();
                final String inputEmail = email.getText().toString().trim();

                if (validateInput(inputName, inputPw, inputEmail))
                    registerUser(inputName, inputPw, inputEmail);

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                finish();
            }
        });
    }

    private void initializeGUI() {

        joinus = findViewById(R.id.ivJoinUs);
        username = findViewById(R.id.atvUsernameReg);
        email = findViewById(R.id.atvEmailReg);
        password = findViewById(R.id.atvPasswordReg);
        signin = findViewById(R.id.tvSignIn);
        signup = findViewById(R.id.btnSignUp);
        progressDialog = new ProgressDialog(this);

//        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void registerUser(final String inputName, final String inputPw, String inputEmail) {

        progressDialog.setMessage("Verificating...");
        progressDialog.show();

        startActivity(new Intent(RegistrationActivity.this, BarCodeActivity.class));
        finish();

//            firebaseAuth.createUserWithEmailAndPassword(inputEmail,inputPw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        progressDialog.dismiss();
//                        sendUserData(inputName, inputPw);
//                        Toast.makeText(RegistrationActivity.this,"You've been registered successfully.", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(RegistrationActivity.this,BarCodeActivity.class));
//                    }
//                    else{
//                        progressDialog.dismiss();
//                        Toast.makeText(RegistrationActivity.this,"Email already exists.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
    }

    private void sendUserData(String username, String password) {

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference users = firebaseDatabase.getReference("users");
//        UserProfile user = new UserProfile(username, password);
//        users.push().setValue(user);
    }

    private boolean validateInput(String inName, String inPw, String inEmail) {
        if (inName.isEmpty()) {
            username.setError("Username is empty.");
            return false;
        }
        if (inPw.isEmpty()) {
            password.setError("Password is empty.");
            return false;
        }
        if (inEmail.isEmpty()) {
            email.setError("Email is empty.");
            return false;
        }
        return true;
    }
}
