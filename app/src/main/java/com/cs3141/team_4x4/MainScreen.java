package com.cs3141.team_4x4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button creditButton = (Button)findViewById(R.id.CreditsButton);
        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, InfoScreen.class);
                intent.putExtra("button", 1);
                startActivity(intent);
            }
        });

        Button highScoreButton = (Button)findViewById(R.id.HighScoresButton);
        highScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, InfoScreen.class);
                intent.putExtra("button", 2);
                startActivity(intent);
            }
        });

        Button playButton = (Button)findViewById(R.id.PlayButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, InfoScreen.class);
                intent.putExtra("button", 3);
                startActivity(intent);
            }
        });
    }

}
