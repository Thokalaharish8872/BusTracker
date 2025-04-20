package com.example.bustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class BusDriverLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bus_driver_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
                        auth.signInWithEmailAndPassword(studentEmail,studentPassword).addOnCompleteListener(BusDriverLogIn.this,task -> {

                            if(task.isSuccessful()){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("IsLoggedIn",true);
                                editor.apply();

                                startActivity(new Intent(BusDriverLogIn.this,DriverMainPage.class));
                            }
                        }).addOnFailureListener(e -> {
                            if(e instanceof FirebaseAuthInvalidCredentialsException) Toast.makeText(BusDriverLogIn.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                            else if(e instanceof FirebaseAuthInvalidUserException) Toast.makeText(BusDriverLogIn.this,"Account not Registered",Toast.LENGTH_SHORT).show();
                            else Toast.makeText(BusDriverLogIn.this,"Error :"+e.getMessage(),Toast.LENGTH_SHORT).show();
                        });
                    }
                    else Toast.makeText(BusDriverLogIn.this,"Password Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(BusDriverLogIn.this,"UserName Cannot be Empty",Toast.LENGTH_SHORT).show();
            }
        });

        signUpPagebtn.setOnClickListener(view ->  startActivity(new Intent(BusDriverLogIn.this,SignUp.class)));
    }
}