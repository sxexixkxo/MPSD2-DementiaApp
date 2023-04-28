package com.example.mpsd2groupassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ReadingCorner extends AppCompatActivity implements View.OnClickListener {
    CardView symptomsCard, communicationCard, dealingCard;
    Intent intent_symptoms, intent_communication, intent_dealing, intent_cert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_corner);

        symptomsCard= (CardView) findViewById(R.id.symptoms_card);
        communicationCard= (CardView) findViewById(R.id.communication_card);
        dealingCard= (CardView) findViewById(R.id.dealing_card);

        symptomsCard.setOnClickListener(this);
        communicationCard.setOnClickListener(this);
        dealingCard.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){

        switch(v.getId()){
            case R.id.symptoms_card:
                intent_symptoms= new Intent(this, DementiaSymptoms.class);
                startActivity(intent_symptoms);
                break;

            case R.id.communication_card:
                intent_communication= new Intent(this, CommunicationTips.class);
                startActivity(intent_communication);
                break;

            case R.id.dealing_card:
                intent_dealing= new Intent(this, DealingTips.class);
                startActivity(intent_dealing);
                break;
        }
    }

}
