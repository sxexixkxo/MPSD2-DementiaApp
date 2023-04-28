package com.example.mpsd2groupassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class DealingTips extends AppCompatActivity {

    Button viewCertButton;
    ProgressBar dealingProgressBar;
    Intent certIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealing_tips);

        viewCertButton= (Button) findViewById(R.id.view_cert_button);

        dealingProgressBar= (ProgressBar) findViewById(R.id.dealingProgressBar);

        viewCertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dealingProgressBar.incrementProgressBy(34);
                certIntent= new Intent(DealingTips.this, Certification.class);
                startActivity(certIntent);
            }
        });
    }
}
