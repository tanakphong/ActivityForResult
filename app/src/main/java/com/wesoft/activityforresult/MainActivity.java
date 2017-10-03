package com.wesoft.activityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    private TextView TxtResult;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxtResult = (TextView) findViewById(R.id.txtDisplayResult);
        Button Second = (Button) findViewById(R.id.btnSecond);
        Button Third = (Button) findViewById(R.id.btnThird);

        Second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                startActivityForResult(i, 2);
            }
        });
        Third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivityForResult(i, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            String ActReturn;
            String result = data.getStringExtra("RETURN");
            switch (requestCode) {
                case 2:
                    ActReturn = "Return Form Second Activity : ";
                    break;
                case 3:
                    ActReturn = "Return Form Third Activity : ";
                    break;
                default:
                    ActReturn = "requestCode not found. ";
            }
            TxtResult.setText(ActReturn + result);
        } else if (resultCode == Activity.RESULT_CANCELED) {
            TxtResult.setText("Cancel");
        }
    }
}
