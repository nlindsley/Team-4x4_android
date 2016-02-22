package com.cs3141.team_4x4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Fetch references to D-pad buttons
        ImageButton down = (ImageButton) findViewById(R.id.buttonDown);
        ImageButton left = (ImageButton) findViewById(R.id.buttonLeft);
        ImageButton right = (ImageButton) findViewById(R.id.buttonRight);
        ImageButton up = (ImageButton) findViewById(R.id.buttonUp);


        // Set listeners for buttons
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Can be changed to perform functions
                ImageView image = (ImageView) findViewById(R.id.imageKnight);
                image.setY(image.getY() + 10);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) findViewById(R.id.imageKnight);
                image.setX(image.getX() - 10);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) findViewById(R.id.imageKnight);
                image.setX(image.getX() + 10);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) findViewById(R.id.imageKnight);
                image.setY(image.getY() - 10);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
