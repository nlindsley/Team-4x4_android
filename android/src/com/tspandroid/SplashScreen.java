package com.tspandroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer = new Thread(){
            public void run() {
                try {
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this, MainScreen.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
