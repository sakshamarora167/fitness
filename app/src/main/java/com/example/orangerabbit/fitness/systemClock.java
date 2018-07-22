package com.example.orangerabbit.fitness;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TimePicker;
import android.support.v7.widget.Toolbar;
//import com.example.orangerabbit.fitness.waterReminder;

import java.util.Arrays;
import java.util.Calendar;

public class systemClock extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker timePicker;
    TextView clockDone,clockCancel;
    Context context;
    PendingIntent pending_intent;
    String hour_string,minute_string;
    waterReminder parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_clock);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.88),(int)(height*0.55));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        getWindow().setAttributes(params);

        this.context=this;
        final Intent my_intent = new Intent(this.context,alarmReciever.class);

        clockCancel=findViewById(R.id.clockCancel);
        clockDone = findViewById(R.id.clockDone);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        timePicker = findViewById(R.id.timePicker);
        final Calendar calendar = Calendar.getInstance();

        clockDone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(Calendar.MINUTE,timePicker.getMinute());

                String AM_PM = " AM";
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                hour_string = String.valueOf(hour);
                minute_string = String.valueOf(minute);

                if(hour>12){
                    hour_string=String.valueOf(hour-12);
                    AM_PM =" PM";
                }
                if( minute<10){
                    minute_string="0"+String.valueOf(minute);
                }
                my_intent.putExtra("extra","alarm on");

                pending_intent = PendingIntent.getBroadcast(systemClock.this,0,my_intent,PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);

                Intent data = new Intent();
                data.putExtra("Data", hour_string+":"+minute_string+ AM_PM);
               // data.putExtra("Data2", minute_string);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        
        clockCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_intent.putExtra("extra","alarm off");
                sendBroadcast(my_intent);
                finish();

            }
        });
    }
}
