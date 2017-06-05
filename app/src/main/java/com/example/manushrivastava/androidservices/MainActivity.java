package com.example.manushrivastava.androidservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String msg = "Android : ";
    MyBoundedService mservice;
    boolean mbound=false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));

    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }
    ServiceConnection mconnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundedService.LocalBinder bind=(MyBoundedService.LocalBinder)service;
            mservice=bind.getService();
            mbound=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mbound=false;

        }
    };
    public void startBoundedService(View view)
    {
             Intent i=new Intent(getBaseContext(),MyBoundedService.class);
             bindService(i,mconnection, Context.BIND_AUTO_CREATE);
    }
    public void stopBoundedService(View view)
    {
        if(mbound)
            unbindService(mconnection);

    }
    public void getrandomno(View view)
    {
        int no=mservice.getRandomNumber();
        Toast.makeText(this, "random no is "+no, Toast.LENGTH_LONG).show();
    }
}
