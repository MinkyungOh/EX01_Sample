package com.example.omin.ex01_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TAG = MainActivity.class.getSimpleName();

    private Button mMinusButton, mPlusButton, mOrderButton;
    private TextView mQuantityTextView, mResultTextView;

    private int mQuantity;
    private int mPrice = 3000;
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

        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuantity<=QUANTITY_MIN) return;
                mQuantity--;
                displayResult();
            }
        });
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuantity>=QUANTITY_MAX) return;
                mQuantity++;
                displayResult();
            }
        });
        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuantity==0) return;
                // 주문 메시지
                String message = mResultTextView.getText().toString() + "\n 주문이 완료되었습니다. 감사합니다.";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                // 초기화
                mQuantityTextView.setText("0");
                mResultTextView.setText("총액: 0원");
                mQuantity = 0;
            }
        });
    }

    private void displayResult() {
        mQuantityTextView.setText(String.valueOf(mQuantity));
        mResultTextView.setText("총액: "+String.valueOf(mQuantity*mPrice)+"원");
    }
}
