package com.example.bustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        TextInputEditText studentIdInputText = findViewById(R.id.StudentId);
        TextInputEditText studentPasswordInputText = findViewById(R.id.StudentPassword);
        Button login = findViewById(R.id.LogInBtn);
        TextView forgotPassword = findViewById(R.id.Forgotbtn);
        TextView signUpPagebtn = findViewById(R.id.SignUpBtn);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentEmail = studentIdInputText.getText().toString(), studentPassword = studentPasswordInputText.getText().toString();

                if(!studentEmail.isEmpty()){
                    if(!studentPassword.isEmpty()){
                        auth.signInWithEmailAndPassword(studentEmail,studentPassword).addOnCompleteListener(Login.this,task -> {

                            if(task.isSuccessful()){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("IsLoggedIn",true);
                                editor.apply();

                                startActivity(new Intent(Login.this,ActivityForFragments.class));
                            }
                        }).addOnFailureListener(e -> {
                            if(e instanceof FirebaseAuthInvalidCredentialsException) Toast.makeText(Login.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                            else if(e instanceof FirebaseAuthInvalidUserException) Toast.makeText(Login.this,"Account not Registered",Toast.LENGTH_SHORT).show();
                            else Toast.makeText(Login.this,"Error :"+e.getMessage(),Toast.LENGTH_SHORT).show();
                        });
                    }
                    else Toast.makeText(Login.this,"Password Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(Login.this,"UserName Cannot be Empty",Toast.LENGTH_SHORT).show();
            }
        });

        signUpPagebtn.setOnClickListener(view ->  startActivity(new Intent(Login.this,SignUp.class)));
    }
}