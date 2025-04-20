package com.example.bustracker;

import android.media.MediaCodec;
import android.media.MediaSync;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonResponse extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_response);

        ListView listView = findViewById(R.id.listview);
        EditText text = findViewById(R.id.edittext);
        Button btn = findViewById(R.id.button);

        text.setText("Google Pixel 6 Pro");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text!=null){

                    String id = MobileDetailsData.getInstance(JsonResponse.this).getID(JsonResponse.this,text.getText().toString());
//                    Toast.makeText(JsonResponse.this,"Phone Id = "+id,Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}