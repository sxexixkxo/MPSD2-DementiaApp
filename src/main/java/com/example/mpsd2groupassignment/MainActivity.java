package com.example.mpsd2groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    CardView readingCornerCard, profileCard;
    Intent readingCornerIntent, profileIntent;


    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();
        readingCornerCard= (CardView) findViewById(R.id.readingCorner);
        profileCard= (CardView) findViewById(R.id.profile);


        if(auth.getCurrentUser()==null){

            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }


        //reading corner card view
        readingCornerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readingCornerIntent= new Intent(MainActivity.this, ReadingCorner.class);
                startActivity(readingCornerIntent);
            }
        });

        //profile card view
        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileIntent= new Intent(MainActivity.this, Profile.class);
                startActivity(profileIntent);
            }
        });

    }
}