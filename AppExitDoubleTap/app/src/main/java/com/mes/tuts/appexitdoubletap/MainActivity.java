package com.mes.tuts.appexitdoubletap;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        //exit is currently false
        if(exit){
            //call finish only if the exit is true
            super.onBackPressed();
            return;
        }
        //exit now gets initialized to true.
        exit =true;
        //press back key again and finish will be called

        Toast.makeText(MainActivity.this, "Press again to exit", Toast.LENGTH_SHORT).show();

        //PostDelayed runnable will run the code after the specified time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // exit will be automatically initalized to false if there is no back press detected within the specified time
                exit = false;
            }
        },2000);
    }
}
