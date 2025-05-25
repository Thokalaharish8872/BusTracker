package com.example.bustracker;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private static boolean pressedDouble = false;
    Handler handler = new Handler();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    SharedPreferences sharedPreferences;



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
        sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentEmail = studentIdInputText.getText().toString(), studentPassword = studentPasswordInputText.getText().toString();

                if(!studentEmail.isEmpty()){
                    if(!studentPassword.isEmpty()){
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child("Students");

                        reference = reference.child(studentEmail.substring(0,studentEmail.length()-10).toUpperCase());
                        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                DataSnapshot snapshot = task.getResult();

                                if(task.getResult().exists()){
                                    login(studentEmail,studentPassword);

                                }
                                else{
                                    Toast.makeText(Login.this,"User Does not exist",Toast.LENGTH_SHORT).show();

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"failure",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else Toast.makeText(Login.this,"Password Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(Login.this,"UserName Cannot be Empty",Toast.LENGTH_SHORT).show();
            }
        });

        signUpPagebtn.setOnClickListener(view ->  startActivity(new Intent(Login.this,SignUp.class)));
    }

    @Override
    public void onBackPressed() {
        if(pressedDouble){
            super.onBackPressed();
            return;
        }
        Toast.makeText(Login.this,"Press back again to exit",Toast.LENGTH_SHORT).show();

        pressedDouble = true;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pressedDouble = true;
            }
        },2000);
    }

    public void login(String studentEmail,String studentPassword){
        auth.signInWithEmailAndPassword(studentEmail,studentPassword).addOnCompleteListener(Login.this,task -> {
            if(task.isSuccessful()){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsLoggedIn",true);
                editor.putInt("UserType",2);
                editor.apply();
                startActivity(new Intent(Login.this,ActivityForFragments.class));
            }
        }).addOnFailureListener(e -> {
            if(e instanceof FirebaseAuthInvalidCredentialsException) Toast.makeText(Login.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
            else if(e instanceof FirebaseAuthInvalidUserException) Toast.makeText(Login.this,"Account not Registered",Toast.LENGTH_SHORT).show();
            else Toast.makeText(Login.this,"Error :"+e.getMessage(),Toast.LENGTH_SHORT).show();
        });
    }
}
