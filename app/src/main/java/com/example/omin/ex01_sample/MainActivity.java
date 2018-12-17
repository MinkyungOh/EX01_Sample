package com.example.omin.ex01_sample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = MainActivity.class.getSimpleName();

    private Button mMinusButton, mPlusButton, mOrderButton;
    private TextView mQuantityTextView, mResultTextView;

    private int mQuantity;
    private int COFFEE_PRICE = 3000;
    private final int QUANTITY_MIN = 0;
    private final int QUANTITY_MAX = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        mMinusButton = (Button)findViewById(R.id.minus_button);
        mPlusButton = (Button)findViewById(R.id.plus_button);
        mOrderButton = (Button)findViewById(R.id.order_button);
        mQuantityTextView = (TextView)findViewById(R.id.quantity_text);
        mResultTextView = (TextView)findViewById(R.id.result_text);

        mMinusButton.setOnClickListener(this);
        mPlusButton.setOnClickListener(this);
        mOrderButton.setOnClickListener(this);
        ((Button)findViewById(R.id.ok_button)).setOnClickListener(this);

    }

    private void displayResult() {
        mQuantityTextView.setText(String.valueOf(mQuantity));
        mResultTextView.setText("총액: "+String.valueOf(mQuantity*COFFEE_PRICE)+"원");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minus_button:
                if(mQuantity<=QUANTITY_MIN) return;
                mQuantity--;
                displayResult();
                break;
            case R.id.plus_button:
                if(mQuantity>=QUANTITY_MAX) return;
                mQuantity++;
                displayResult();
                break;
            case R.id.order_button:
                if(mQuantity==0) return;
                // 주문 메시지
                String message = mResultTextView.getText().toString();
                Intent intent = new Intent(this, OrderCheckActivity.class);
                intent.putExtra("result", message);
                intent.putExtra("comment", "");
                startActivity(intent);

                break;
            case R.id.ok_button:
                if(mQuantity==0) return;
                String[] addresses = {"omin@nate.com"};
                composeEmail(addresses,
                        "주문 요청",
                        mResultTextView.getText().toString());

                // 초기화
                mQuantityTextView.setText("0");
                mResultTextView.setText("총액: 0원");
                mQuantity = 0;

                break;
        }
    }

    private void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
