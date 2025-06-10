package com.example.bustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdministratorLogin extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference reference;

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
        TextInputEditText adminName  = findViewById(R.id.AdminName);
        AppCompatButton login = findViewById(R.id.LogInBtn);
        TextView forgotPassword = findViewById(R.id.Forgotbtn);


        sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameStr = adminName.getText().toString(), Email = studentIdInputText.getText().toString(), Password = studentPasswordInputText.getText().toString();
                reference = FirebaseDatabase.getInstance().getReference("Users").child("Administrators");

                if (!NameStr.isEmpty()) {
                    if (!Email.isEmpty()) {
                        if (!Password.isEmpty()) {

                            reference = reference.child(NameStr);
                            reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                                    if(task.isSuccessful()) {
                                        DataSnapshot snapshot = task.getResult();
                                        if (snapshot.exists()) {
                                            String fetchedEmail = snapshot.child("email").getValue(String.class);

                                            if (fetchedEmail != null && fetchedEmail.equals(Email)) {
                                                Toast.makeText(AdministratorLogin.this, "User Exist", Toast.LENGTH_SHORT).show();

                                                login(Email, Password);
                                            } else {
                                                Toast.makeText(AdministratorLogin.this, "Email doesn't match", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(AdministratorLogin.this, "User does not exist", Toast.LENGTH_SHORT).show();
                                        }
//                                    }
//                                    else{
//                                        Toast.makeText(AdministratorLogin.this,"not success",Toast.LENGTH_SHORT).show();
//                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AdministratorLogin.this, "failure", Toast.LENGTH_SHORT).show();

                                }
                            });
                        } else
                            Toast.makeText(AdministratorLogin.this, "Password Cannot be Empty", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(AdministratorLogin.this, "Email Cannot be Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AdministratorLogin.this, "UserName Cannot be Empty", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private void login(String studentEmail,String studentPassword){
        auth.signInWithEmailAndPassword(studentEmail,studentPassword).addOnCompleteListener(AdministratorLogin.this,task -> {

            if(task.isSuccessful()){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("IsLoggedIn",true);
                editor.putInt("UserType",4);
                editor.apply();

                startActivity(new Intent(AdministratorLogin.this,ActivityForAdministrator.class));
                finish();
            }
        }).addOnFailureListener(e -> {
            if(e instanceof FirebaseAuthInvalidCredentialsException) Toast.makeText(AdministratorLogin.this,"Incorrect Password"+studentPassword,Toast.LENGTH_SHORT).show();
            else if(e instanceof FirebaseAuthInvalidUserException) Toast.makeText(AdministratorLogin.this,"Account not Registered",Toast.LENGTH_SHORT).show();
            else Toast.makeText(AdministratorLogin.this,"Error :"+e.getMessage(),Toast.LENGTH_SHORT).show();
        });
    }
}