package com.capstone.truckerfrontend.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.capstone.truckerfrontend.R;

public class HubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
    }

    public void onClick(View view) {
        String text = ((Button) view).getText().toString();
        if (text.equals(getResources().getString(R.string.filter_loads_text))) {
            Intent redirectIntent = new Intent(this, FilterLoadsActivity.class);
            startActivity(redirectIntent);
        } else if (text.equals(getResources().getString(R.string.current_loads_text))) {
            Intent redirectIntent = new Intent(this, CurrentLoadsActivity.class);
            startActivity(redirectIntent);
        } else if (text.equals(getResources().getString(R.string.manage_account_text))) {
            Intent redirectIntent = new Intent(this, ManageAccountActivity.class);
            startActivity(redirectIntent);
        }
    }
}
