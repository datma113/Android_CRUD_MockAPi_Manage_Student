package com.example.android_crud_login_mockapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtEmail, edtPassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_btnLogin);
        edtEmail = findViewById(R.id.log_edtEmail);
        edtPassword = findViewById(R.id.log_password);

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if( email.length() > 5 && password.length() > 5 ) {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(LoginActivity.this, ManageStudent.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }

            }
        });

    }
}