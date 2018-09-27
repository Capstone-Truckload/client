package com.capstone.truckerfrontend.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.truckerfrontend.R;
import com.capstone.truckerfrontend.models.HashedPassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AccountCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
    }

    public void onClick(View view) {
        EditText nameInput = findViewById(R.id.nameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText emailInput = findViewById(R.id.emailInput);
        String name = nameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String email = emailInput.getText().toString();
        int iterations = getResources().getInteger(R.integer.hashing_iterations);
        int keyLength = getResources().getInteger(R.integer.password_hash_length);

        if (validate(name, password, email)) {
            HashedPassword hash = new HashedPassword(password, name, iterations, keyLength);
            register(name, hash.string);
        }
    }

    private Boolean validate(String userName, String userPassword, String userEmail) {
        Boolean result = false;

        if(userName.isEmpty() || userPassword.isEmpty() || userEmail.isEmpty())
        {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }

        return result;
    }

    private void register(String userName, String userPassword)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent loginIntent = new Intent(AccountCreationActivity.this, HubActivity.class);
                    startActivity(loginIntent);
                }
                else
                {
                    Toast.makeText(AccountCreationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
