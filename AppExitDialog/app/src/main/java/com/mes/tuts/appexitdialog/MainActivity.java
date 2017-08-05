package com.mes.tuts.appexitdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Override onBackPressed method to detect when user presses the back key.
    //By default it will just finish the activity.

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //Note: calling the super.onBackPressed will invoke the default behavior of this method from parent class, i.e finish activity

        //handle your App exit confirmation using a simple Alertdialog builder
        confirmExit();
    }

    private void confirmExit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        alertDialogBuilder.setMessage("Do you really want to exit??");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alertDialogBuilder.show();
    }
}
