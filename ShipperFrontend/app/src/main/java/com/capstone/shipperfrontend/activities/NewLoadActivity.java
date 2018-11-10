package com.capstone.shipperfrontend.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.capstone.shipperfrontend.R;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.shipperfrontend.R;
import com.capstone.shipperfrontend.models.HashedPassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_load);
    }

    protected void onClick(View view){
        EditText loadName = findViewById(R.id.loadName);
        EditText origin = findViewById(R.id.originAddress);
        EditText destination = findViewById(R.id.destinationAddress);

    }
}
