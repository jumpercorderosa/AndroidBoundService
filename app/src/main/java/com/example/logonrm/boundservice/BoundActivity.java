package com.example.logonrm.boundservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BoundActivity extends AppCompatActivity {

    BoundService mBoundService;
    boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_activity);

        final TextView timestampText = (TextView) findViewById(R.id.tvTimestamp);
        Button printTimestampButton = (Button) findViewById(R.id.btPrint);
        final Button stopServiceButton = (Button) findViewById(R.id.btStop);

        printTimestampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mServiceBound) {
                    timestampText.setText(mBoundService.getTimestamp());
                }
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mServiceBound) {
                    unbindService(mServiceConnection);
                    mServiceBound = false;
                }
                Intent intent = new Intent(BoundActivity.this, BoundService.class);
                stopService(intent);
            }
        });

        onStart(){
            super.onStart();
            
        }

    }
}
