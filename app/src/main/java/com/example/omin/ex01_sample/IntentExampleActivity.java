package com.example.omin.ex01_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IntentExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE = 1000;
    private EditText mIdEditText, mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_example);

        mIdEditText = (EditText)findViewById(R.id.id_edit);
        mPasswordEditText = (EditText)findViewById(R.id.password_edit);

        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, IntentExample2Activity.class);
        intent.putExtra("id", mIdEditText.getText().toString());
        intent.putExtra("password", mPasswordEditText.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String text = data.getStringExtra("result");
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}
