package com.example.scray.breakthesilencev3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SpalashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalash_screen);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                doWork();
                nextActivity();
            }
        });
        thread.start();
    }
    private void doWork()
    {
        try
        {
            Thread.sleep(2000);

        }catch (Exception e)
        {
            e.printStackTrace();
        };
    }
    private void nextActivity()
    {
        try
        {
            Intent intent = new Intent(this,BottomNavigationActivity.class);
            startActivity(intent);
            finish();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
