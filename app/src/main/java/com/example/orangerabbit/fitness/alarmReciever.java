package com.example.orangerabbit.fitness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class alarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receiver Intent","Hey there :)");

        String getString = intent.getExtras().getString("extra");

        Log.e("What is the key ?",getString);

        Intent service_intent =  new Intent(context,Notifications.class);

        service_intent.putExtra("extra",getString);

        context.startService(service_intent);

    }
}
