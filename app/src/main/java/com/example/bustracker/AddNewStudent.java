package com.example.bustracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

import java.util.ArrayList;
import java.util.Objects;

public class AddNewStudent extends AppCompatActivity {

    DatabaseReference reference,reference2;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String nameStr,emailStr,idStr,boardingPointStr,feeStructureStr,passValidFromStr,passValidTillStr;
    Spinner yearSpinner,deptSpinner,busNoSpinner,feePaidSpinner;
    boolean flag = false;
    boolean isFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();



        reference = FirebaseDatabase.getInstance().getReference("Users").child("Students");

        yearSpinner = findViewById(R.id.YearSpinner);
        deptSpinner = findViewById(R.id.DeptSpinner);
        busNoSpinner = findViewById(R.id.BusNoSpinner);
        feePaidSpinner = findViewById(R.id.FeePaidSpinner);

        TextInputEditText name = findViewById(R.id.Name);
        TextInputEditText email = findViewById(R.id.Email);
        TextInputEditText id = findViewById(R.id.Id);
        TextInputEditText boardingPoint = findViewById(R.id.BoardingPoint);
        TextInputEditText feeStructure = findViewById(R.id.FeeStructure);
        TextInputEditText passValidFrom = findViewById(R.id.passValidFrom);
        TextInputEditText passValidTill = findViewById(R.id.passValidTill);

        TextView text = findViewById(R.id.text1);

        AppCompatButton btn = findViewById(R.id.Btn);


        if(intent != null && intent.hasExtra("Id")){
            flag = true;

            btn.setText("Edit");
            text.setText("Edit Student Details");

            reference.child(intent.getStringExtra("Id")).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful() && task.getResult().exists()){
                        DataSnapshot result = task.getResult();
                        name.setText(result.child("name").getValue(String.class));
                        email.setText(result.child("email").getValue(String.class));
                        id.setText(result.child("id").getValue(String.class));
                        boardingPoint.setText(result.child("boardingPoint").getValue(String.class));
                        feeStructure.setText(result.child("feeStructure").getValue(String.class));
                        if(result.child("isFeePaid").getValue(String.class).equals("Yes")){
                            feePaidSpinner.setSelection(0);
                        }
                        else if(result.child("isFeePaid").getValue(String.class).equals("No")){
                            feePaidSpinner.setSelection(1);
                        }
                        else{
                            feePaidSpinner.setSelection(2);
                        }
                        switch (result.child("year").getValue(String.class)){
                            case "I" : yearSpinner.setSelection(0); break;
                            case "II": yearSpinner.setSelection(1); break;
                            case "III":yearSpinner.setSelection(2); break;
                            default: yearSpinner.setSelection(3);
                        }
                        switch (result.child("dept").getValue(String.class)){
                            case   "CSE-A"  :  deptSpinner.setSelection(0);  break;
                            case   "CSE-B"  :  deptSpinner.setSelection(1);  break;
                            case   "CSE-C"  :  deptSpinner.setSelection(2);  break;
                            case   "CSD-A"  :  deptSpinner.setSelection(3);  break;
                            case   "CSD-B"  :  deptSpinner.setSelection(4);  break;
                            case   "CSM-A"  :  deptSpinner.setSelection(5);  break;
                            case   "CSM-B"  :  deptSpinner.setSelection(6);  break;
                            case   "CSM-C"  :  deptSpinner.setSelection(7);  break;
                            case   "CSO-A"  :  deptSpinner.setSelection(8);  break;
                            case   "AIM-A"  :  deptSpinner.setSelection(9);  break;
                            case   "AID-A"  :  deptSpinner.setSelection(10);  break;
                            case   "ECE-A"  :  deptSpinner.setSelection(11);  break;
                            case   "CIVIL-A"  :  deptSpinner.setSelection(12);  break;
                            case   "MECH-A"  :  deptSpinner.setSelection(13);  break;
                        }

                        String resultbus = result.child("busNo").getValue(String.class);
                        for(int i=1;i<=18;i++){
                           if((i+"").equals(resultbus)){
                               busNoSpinner.setSelection(i);
                               break;
                           }
                        }

                        String resultYear = result.child("year").getValue(String.class);

                        switch (resultYear){
                            case "I" : yearSpinner.setSelection(0); break;
                            case "II" : yearSpinner.setSelection(1); break;
                            case "III" : yearSpinner.setSelection(2); break;
                            case "IV" : yearSpinner.setSelection(3); break;
                        }

                        passValidFrom.setText(result.child("passValidFrom").getValue(String.class));
                        passValidTill.setText(result.child("passValidTill").getValue(String.class));



                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }
        if(intent!=null){
            isFlag = true;
            btn.setText("Request Admin for SignUp");
            text.setText("Sign Up");
            email.setText(intent.getStringExtra("Email"));
        }

        String[] yearArray = new String[]{"I", "II", "III", "IV"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddNewStudent.this, android.R.layout.simple_list_item_1,yearArray);
        yearSpinner.setAdapter(adapter);

        ArrayList<String> DeptArray = new ArrayList<>();
        DeptArray.add("CSE-A");
        DeptArray.add("CSE-B");
        DeptArray.add("CSE-C");
        DeptArray.add("CSD-A");
        DeptArray.add("CSD-B");
        DeptArray.add("CSM-A");
        DeptArray.add("CSM-B");
        DeptArray.add("CSM-C");
        DeptArray.add("CSO-A");
        DeptArray.add("AIM-A");
        DeptArray.add("AID-A");
        DeptArray.add("ECE-A");
        DeptArray.add("CIVIL-A");
        DeptArray.add("MECH-A");

        adapter = new ArrayAdapter<>(AddNewStudent.this, android.R.layout.simple_list_item_1,DeptArray);
        deptSpinner.setAdapter(adapter);

        ArrayList<String> BusNoArray = new ArrayList<>();
        for(int i=1;i<=18;i++){
            BusNoArray.add(i+"");
        }

        adapter = new ArrayAdapter<>(AddNewStudent.this, android.R.layout.simple_list_item_1,BusNoArray);
        busNoSpinner.setAdapter(adapter);

        String[] FeePaidArray = new String[]{"Yes","No","Partially Paid"};
        adapter = new ArrayAdapter<>(AddNewStudent.this, android.R.layout.simple_list_item_1,FeePaidArray);
        feePaidSpinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameStr = name.getText().toString();
                emailStr = email.getText().toString();
                idStr = id.getText().toString();
                boardingPointStr = boardingPoint.getText().toString();
                feeStructureStr = feeStructure.getText().toString();
                passValidFromStr = passValidFrom.getText().toString();
                passValidTillStr = passValidTill.getText().toString();
                if(!nameStr.isEmpty()){
                    if(!emailStr.isEmpty()){
                        if(!idStr.isEmpty()){
                            if(!boardingPointStr.isEmpty()){
                                if(!feeStructureStr.isEmpty()) {
                                    if (!passValidFromStr.isEmpty()) {
                                        if(!passValidTillStr.isEmpty()) {
                                            if (isFlag) {
                                                reference2 = FirebaseDatabase.getInstance().getReference("Users").child("StudentRequests");
                                                editStudent("Registration Request Sent", reference2);
                                            }
                                            else{

                                            auth.createUserWithEmailAndPassword(emailStr, emailStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        editStudent("Added", reference);
                                                    }
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    if (e instanceof FirebaseAuthUserCollisionException) {
                                                        if (flag) {
                                                            editStudent("Details Updated", reference);
                                                        } else {
                                                            Toast.makeText(AddNewStudent.this, "Account Already Exist", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                    if (e instanceof FirebaseAuthWeakPasswordException)
                                                        Toast.makeText(AddNewStudent.this, "Password too weak", Toast.LENGTH_SHORT).show();
                                                    if (e instanceof FirebaseAuthInvalidCredentialsException)
                                                        Toast.makeText(AddNewStudent.this, "Email not valid", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                        }else{
                                            Toast.makeText(AddNewStudent.this,"Pass Valid Till cannot be Empty",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                    else{
                                        Toast.makeText(AddNewStudent.this,"Pass Valid From cannot be Empty",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(AddNewStudent.this,"Fee Structure Cannot be Empty",Toast.LENGTH_SHORT).show();

                                }
                            }
                            else{
                                Toast.makeText(AddNewStudent.this,"Boarding Point Cannot be Empty",Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(AddNewStudent.this,"Student Id  be Empty",Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(AddNewStudent.this,"Email Cannot Cannot be Empty",Toast.LENGTH_SHORT).show();

                    }

                }
                else{
                    Toast.makeText(AddNewStudent.this,"Student Name Cannot be Empty",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
   private void editStudent(String task,DatabaseReference reference){
        DetailsDataModel dataModel = new DetailsDataModel(nameStr, emailStr, idStr, boardingPointStr, feeStructureStr, yearSpinner.getSelectedItem().toString(), deptSpinner.getSelectedItem().toString(), busNoSpinner.getSelectedItem().toString(), feePaidSpinner.getSelectedItem().toString(),passValidFromStr,passValidTillStr);
        reference.child(idStr).setValue(dataModel);
        Toast.makeText(AddNewStudent.this, "Student "+task+" Successfully", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}