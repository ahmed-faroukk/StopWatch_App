package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  int seconds = 0 ;
    private boolean running ;
    private boolean WasRunning ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null)
        {

            seconds = savedInstanceState.getInt("Seconds");
            running = savedInstanceState.getBoolean("running");
            WasRunning=savedInstanceState.getBoolean("wasrunning");

        }
        runTimer();

    }
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasrunning" , WasRunning);
    }
    protected void onStart()
    {
        super.onStart();
        if (WasRunning)
        {

            running = true;


        }

    }
    protected void onPause()
    {
        super.onPause();
            WasRunning = running ;
            running = false ;
    }
    protected void onResume()
    {
        super.onResume();
        if (WasRunning);
        running = true ;
    }


    protected void onStop()
    {
        super.onStop();
        WasRunning = running ;
        running = false ;

    }


    public void onClickStart(View view)
    {
        running = true ;
    }
    public void onClickStop(View view)
    {
        running = false ;
    }
    public void onClickReset(View view)
    {
        seconds = 0 ;
        running = false ;
    }

    private void runTimer()
    {
        final TextView textView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds /3600 ;
                int minute = (seconds %3600) / 60 ;
                int sec = (seconds %60)  ;

                String time  = String.format("%02d:%02d:%02d" , hours , minute , sec);

                textView.setText(time);
                if(running == true)
                {
                    seconds++;

                }

                handler.postDelayed(this , 1000);

            }
        });



    }


}