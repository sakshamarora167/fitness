package com.example.orangerabbit.fitness;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

public class Notifications extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("Local Service", "Receive start id" + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone extra is ",state);

        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if(!this.isRunning && startId==1){
            Log.e("there is no music","and you want start");
            media_song = MediaPlayer.create(this,R.raw.notification);
            media_song.start();
            this.isRunning=true;
            this.startId=0;

            NotificationManager notify_manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

            Intent intent_activity = new Intent(this.getApplicationContext(),activity_water.class);

            PendingIntent pending_intent = PendingIntent.getActivity(this,0,intent_activity,0);


            Notification notification_popup = new Notification.Builder(this).setContentTitle("Drink a glass of water")
                    .setContentText("Click me!") .setSmallIcon(R.drawable.ic_invert_colors_black_24dp).setContentIntent(pending_intent).setPriority(Notification.PRIORITY_HIGH).setAutoCancel(true).build();

//            startForeground(0, notification_popup);

            notify_manager.notify(0,notification_popup);

        }
        else if(this.isRunning && startId==0){
            Log.e("there is music","and you want end");
            media_song.stop();
            media_song.reset();
            this.isRunning=false;
            this.startId=0;
        }
        else if(!this.isRunning && startId==0){
            Log.e("there is no music","and you want end");
            this.isRunning=false;
            this.startId=0;

        }
        else if(this.isRunning && startId==1){
            Log.e("there is no music","and you want start");
            this.isRunning=true;
            this.startId=1;
        }
        else{
            Log.e("there is no music","and you want start");
        }




        return START_NOT_STICKY;
    }

    public void onDestroy(){
        Log.e("onDestroyCalled","ta da");
        super.onDestroy();
        this.isRunning=false;
    }
}
