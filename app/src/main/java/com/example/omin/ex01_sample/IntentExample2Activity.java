package com.example.omin.ex01_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntentExample2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_example2);

        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            String password = intent.getStringExtra("password");

            String message = "id: " + id + " password: " + password;
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        String text = ((Button)v).getText().toString();
        Intent intent = new Intent();
        intent.putExtra("result", text);

        setResult(RESULT_OK, intent);
        finish();
    }
}
