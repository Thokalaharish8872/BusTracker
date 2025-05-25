package com.example.bustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class SignUp extends AppCompatActivity {

    private static boolean pressedDouble = false;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        TextInputEditText studentIdInputText = findViewById(R.id.StudentId);
        TextInputEditText studentPasswordInputText = findViewById(R.id.StudentPassword);
        Button signupBtn = findViewById(R.id.SignUpBtn);
        TextView loginPagebtn = findViewById(R.id.LogInBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentEmail = studentIdInputText.getText().toString(), studentPassword = studentPasswordInputText.getText().toString();

                if(!studentEmail.isEmpty()){
                    if(!studentPassword.isEmpty()){
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.createUserWithEmailAndPassword(studentEmail,studentPassword).addOnCompleteListener(SignUp.this,task -> {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this,"Registration Success",Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("IsLoggedIn",true);
                                editor.putString("userEmail",studentEmail);
                                editor.apply();

                                startActivity(new Intent(SignUp.this,ActivityForFragments.class));
                                finish();
                            }
                            else{
                                Log.d("error","Failure : "+task.getException());
                                Toast.makeText(SignUp.this,"Failure : "+task.getException(),Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(e -> {
                            if(e instanceof FirebaseAuthUserCollisionException) Toast.makeText(SignUp.this,"Account Already Exist",Toast.LENGTH_SHORT).show();
                            if(e instanceof FirebaseAuthWeakPasswordException) Toast.makeText(SignUp.this,"Password too weak",Toast.LENGTH_SHORT).show();
                            if(e instanceof FirebaseAuthInvalidCredentialsException) Toast.makeText(SignUp.this,"Email not valid",Toast.LENGTH_SHORT).show();
                        });
                    }
                    else Toast.makeText(SignUp.this,"Password Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(SignUp.this,"UserName Cannot be Empty",Toast.LENGTH_SHORT).show();
            }
        });

        loginPagebtn.setOnClickListener(view -> startActivity(new Intent(SignUp.this,Login.class)));
    }

    @Override
    public void onBackPressed() {
        if(pressedDouble){
            super.onBackPressed();
            return;
        }
        Toast.makeText(SignUp.this,"Press again back to exit",Toast.LENGTH_SHORT).show();

        pressedDouble = true;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pressedDouble = true;
            }
        },2000);



    }
}