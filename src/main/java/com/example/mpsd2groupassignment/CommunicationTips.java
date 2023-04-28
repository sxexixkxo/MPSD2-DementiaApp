package com.example.mpsd2groupassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class CommunicationTips extends AppCompatActivity {

    Button doneButton2;
    ProgressBar communicationProgressBar;
    Intent intentNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communication_tips);

        doneButton2= (Button) findViewById(R.id.done_button_2);

        communicationProgressBar= (ProgressBar) findViewById(R.id.communicationProgressBar);

        doneButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communicationProgressBar.incrementProgressBy(33);
                intentNext= new Intent(CommunicationTips.this, DealingTips.class);
                startActivity(intentNext);
            }
        });

    }
}
