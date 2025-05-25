package com.example.bustracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditExistingStudentDetails extends AppCompatActivity {
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_existing_student_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText id = findViewById(R.id.StudentId);
        TextInputEditText name = findViewById(R.id.StudentName);

        AppCompatButton searchBtn = findViewById(R.id.Search);
        AppCompatButton nextBtn = findViewById(R.id.Next);

        nextBtn.setClickable(false);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!id.getText().toString().isEmpty()){
                    reference = FirebaseDatabase.getInstance().getReference("Users").child("Students");
                    reference = reference.child(id.getText()+"");
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.getResult().exists()){
                                    name.setText(task.getResult().child("name").getValue(String.class));
                                    nextBtn.setClickable(true);
                            }
                            else{
                                Toast.makeText(EditExistingStudentDetails.this,"User Does not Exist",Toast.LENGTH_SHORT).show();
                                name.setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditExistingStudentDetails.this,"Unexpected Error",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else{
                    Toast.makeText(EditExistingStudentDetails.this,"Please Enter Student Id",Toast.LENGTH_SHORT).show();
                }
            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditExistingStudentDetails.this,AddNewStudent.class);
                i.putExtra("Id",id.getText().toString());
                startActivity(i);
            }
        });
    }
}