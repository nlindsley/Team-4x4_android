package com.tspandroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class InfoScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int caller = this.getIntent().getIntExtra("button", 0);

        switch (caller) {
            case 0:
                finish();

            case 1:
                setContentView(R.layout.credits_screen);
                break;

            case 2:
                setContentView(R.layout.high_score_screen);
                break;

            case 3:
                setContentView(R.layout.story_screen);
                findViewById(R.id.StartButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.tutorial_screen);
                        findViewById(R.id.TutorialStartButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(InfoScreen.this, AndroidLauncher.class);
                                startActivity(intent);
                            }
                        });
                    }
                });

                break;
        }
    }

}
