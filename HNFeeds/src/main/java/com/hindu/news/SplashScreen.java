package com.hindu.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.gc.materialdesign.widgets.Dialog;


public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);

       if(!networkCheck()) {
           Dialog dialog = new Dialog(this,"Hindu", "Check your network connection.");
           dialog.show();

       }
        else {
           new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

               @Override
               public void run() {
                   // This method will be executed once the timer is over
                   // Start your app main activity
                   Intent homeIntent = new Intent(SplashScreen.this, HomeScreen.class);
                   startActivity(homeIntent);
                   // close this activity
                   finish();
               }
           }, SPLASH_TIME_OUT);
       }

    }

    private boolean networkCheck() {

        ConnectivityManager cm = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() == null)
            return false;

        return cm.getActiveNetworkInfo().isConnectedOrConnecting();

    }



}
