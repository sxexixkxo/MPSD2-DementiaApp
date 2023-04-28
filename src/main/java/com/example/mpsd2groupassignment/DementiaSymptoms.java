package com.example.mpsd2groupassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class DementiaSymptoms extends AppCompatActivity {
    Button doneButton1;
    ProgressBar symptomsProgressBar;
    Intent nextIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptoms);

        doneButton1= (Button) findViewById(R.id.done_button_1);
        symptomsProgressBar= (ProgressBar) findViewById(R.id.symptomsProgressBar);

        doneButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symptomsProgressBar.incrementProgressBy(33);
                nextIntent= new Intent(DementiaSymptoms.this, CommunicationTips.class);
                startActivity(nextIntent);
            }
        });
    }
}
