package com.example.sheetsapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddItem extends AppCompatActivity {
    Button btnAdd;
    TextInputEditText et_Name, et_Hobby;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        btnAdd = findViewById(R.id.btnAdd);
        et_Name = findViewById(R.id.etName);
        et_Hobby = findViewById(R.id.etHobby);
        btnAdd.setOnClickListener(view -> {
            addItem_Sheet();
        });
    }

    public void addItem_Sheet(){
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
        final String name = Objects.requireNonNull(et_Name.getText()).toString().trim();
        final String hobby = Objects.requireNonNull(et_Hobby.getText()).toString().trim();
        final String url = "https://script.google.com/macros/s/AKfycbxGiSQRV7WnJrYnIAP09uEI_WGqa2S3DCj4bg0iYJ-dcYIhiwl59iR45qNX5Ks9KsOILQ/exec?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("successful", "Inserted");
                        loading.dismiss();
                        Toast.makeText(AddItem.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.getLocalizedMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                //here we pass params
                parmas.put("action","addItem");
                parmas.put("Name", name);
                parmas.put("Hobby", hobby);
                return parmas;
            }
        };

        int socketTimeOut = 50000;//50 Seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}
