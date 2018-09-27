package com.capstone.truckerfrontend.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.capstone.truckerfrontend.R;
import com.capstone.truckerfrontend.models.HashedPassword;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) {
        String text = ((Button) view).getText().toString();
        if (text.equals(getResources().getString(R.string.submit_text))) {
            login();
        } else if (text.equals(getResources().getString(R.string.create_account_text))) {
            newAccount();
        }
    }

    private void newAccount() {
        Intent newAccountIntent = new Intent(this, AccountCreationActivity.class);
        startActivity(newAccountIntent);
    }

    private void login() {
        EditText nameInput = findViewById(R.id.nameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        String name = nameInput.getText().toString();
        String password = passwordInput.getText().toString();
        int iterations = getResources().getInteger(R.integer.hashing_iterations);
        int keyLength = getResources().getInteger(R.integer.password_hash_length);
        HashedPassword hash = new HashedPassword(password, name, iterations, keyLength);

        validateFirebase(name, hash.string);
    }

    private void validateFirebase(String userName, String userPassword)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent loginIntent = new Intent(LoginActivity.this, HubActivity.class);
                    startActivity(loginIntent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
