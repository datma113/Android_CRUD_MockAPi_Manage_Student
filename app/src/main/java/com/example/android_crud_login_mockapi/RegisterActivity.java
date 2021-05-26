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

public class RegisterActivity extends AppCompatActivity {

    Button btnReg;
    EditText edtEmail, edtPassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        btnReg = findViewById(R.id.reg_btnReg);
        edtEmail = findViewById(R.id.reg_edtEmail);
        edtPassword = findViewById(R.id.reg_password);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.length() > 5 && password.length() > 5) {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Successed", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(RegisterActivity.this, "Empty email or password", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}