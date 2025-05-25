package com.example.bustracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class AddNewDriver extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_driver);

        reference = FirebaseDatabase.getInstance().getReference("Users").child("Drivers");

        Spinner busNoSpinner = findViewById(R.id.BusNoSpinner1);
        TextInputEditText name = findViewById(R.id.Name1);
        TextInputEditText email = findViewById(R.id.Email1);
        TextInputEditText phoneNo = findViewById(R.id.PhoneNo1);
        TextInputEditText id = findViewById(R.id.Id1);
        TextInputEditText boardingPoint = findViewById(R.id.BoardingPoint1);
        TextInputEditText numberPlate = findViewById(R.id.NumberPlate1);
        AppCompatButton btn = findViewById(R.id.Btn1);

        TextView text = findViewById(R.id.text2);

        Intent intent = getIntent();


        if(intent != null && intent.hasExtra("busNo")){

            btn.setText("Edit");
            text.setText("Edit Driver Details");

            reference.child(intent.getStringExtra("busNo")).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful() && task.getResult().exists()){
                        DataSnapshot result = task.getResult();
                        name.setText(result.child("name").getValue(String.class));
                        email.setText(result.child("email").getValue(String.class));
                        id.setText(result.child("id").getValue(String.class));
                        boardingPoint.setText(result.child("boardingPoint").getValue(String.class));

                        String resultbus = result.child("busNo").getValue(String.class);
                        for(int i=1;i<=18;i++){
                            if((i+"").equals(resultbus)){
                                busNoSpinner.setSelection(i);
                                break;
                            }
                        }

                        phoneNo.setText(result.child("phoneNo").getValue(String.class));
                        numberPlate.setText(result.child("numberPlate").getValue(String.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }

        ArrayList<String> BusNoArray = new ArrayList<>();

        for(int i=1;i<=18;i++){
            BusNoArray.add(i+"");
        }


        SpinnerAdapter adapter = new ArrayAdapter<>(AddNewDriver.this, android.R.layout.simple_list_item_1,BusNoArray);


        busNoSpinner.setAdapter(adapter);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString(),emailStr = email.getText().toString(),idStr = id.getText().toString(),boardingPointStr = boardingPoint.getText().toString(),phoneNoStr = phoneNo.getText().toString(),numberPlateStr = numberPlate.getText().toString();
                if(!nameStr.isEmpty()){
                    if(!emailStr.isEmpty()){
                        if(!phoneNoStr.isEmpty()){
                            if(!boardingPointStr.isEmpty()){
                                if(!idStr.isEmpty()){
                                    if(!numberPlateStr.isEmpty()) {
                                        auth.createUserWithEmailAndPassword(emailStr, emailStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    DetailsDataModel dataModel = new DetailsDataModel(nameStr, emailStr, phoneNoStr, boardingPointStr, idStr, numberPlateStr);
                                                    reference.child(busNoSpinner.getSelectedItem().toString()).setValue(dataModel);
                                                    Toast.makeText(AddNewDriver.this, "Driver Added Successfully", Toast.LENGTH_SHORT).show();
                                                    onBackPressed();
                                                } else {
                                                    Toast.makeText(AddNewDriver.this, "Driver Add Failure" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                if (e instanceof FirebaseAuthUserCollisionException)
                                                    Toast.makeText(AddNewDriver.this, "Account Already Exist", Toast.LENGTH_SHORT).show();
                                                if (e instanceof FirebaseAuthWeakPasswordException)
                                                    Toast.makeText(AddNewDriver.this, "Password too weak", Toast.LENGTH_SHORT).show();
                                                if (e instanceof FirebaseAuthInvalidCredentialsException)
                                                    Toast.makeText(AddNewDriver.this, "Email not valid", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }
                                    else{
                                        Toast.makeText(AddNewDriver.this,"NumberPlate Cannot be Empty",Toast.LENGTH_SHORT).show();

                                    }
                                }
                                else{
                                    Toast.makeText(AddNewDriver.this,"Id Cannot be Empty",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(AddNewDriver.this,"Boarding Point Cannot be Empty",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(AddNewDriver.this,"Student Phone Number  be Empty",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(AddNewDriver.this,"Email Cannot Cannot be Empty",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(AddNewDriver.this,"Student Name Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}