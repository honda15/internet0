package com.example.internet0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.NetworkInterface;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager cmgr;
    private android.util.Log Log;
    private MyNetworkReceive myNetworkReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        myNetworkReceive = new MyNetworkReceive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myNetworkReceive,filter);
    }

    @Override
    public void finish() {
        unregisterReceiver(myNetworkReceive);
        super.finish();

    }

    private boolean isConnectNetwork(){
        NetworkInfo info = cmgr.getActiveNetworkInfo();
        boolean isConnected = info!=null && info.isConnectedOrConnecting();
        return isConnected;
    }

    private boolean isConnectWifi(){
        NetworkInfo info = cmgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info.isConnected();
    }

    private class MyNetworkReceive extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("brad","isConnected = " + isConnectNetwork());
        }
    }
    public void test1(View view) {
        Log.v("brad","isConnected: " + isConnectNetwork());
        Log.v("brad","isConnectWifi: " + isConnectWifi());
    }
}