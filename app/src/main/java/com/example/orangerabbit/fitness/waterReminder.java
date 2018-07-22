package com.example.orangerabbit.fitness;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.graphics.Point;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
//import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class waterReminder extends AppCompatActivity {

//    View line1,line2;
    TextView timeFrom,timeTo,timeAt,waterFrom,waterTo;
    Spinner numberOfTimes,frequency;
    RelativeLayout uRl,tRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_reminder);

        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.rg);
        final CheckBox checkBox = (CheckBox)findViewById(R.id.cb1);

        final String[] number_of_times = new String[]{
                "Number of times", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten"};

        String[] frequencies = new String[]{
                "Frequency", "20", "30", "40", "50"};
        timeAt = findViewById(R.id.timeAt);
        timeFrom = findViewById(R.id.timeFrom);
        timeTo = findViewById(R.id.timeTo);
        waterFrom = findViewById(R.id.water_from);
        waterTo = findViewById(R.id.water_to);
        numberOfTimes = (Spinner)findViewById(R.id.numberOfTimes);
        frequency = (Spinner)findViewById(R.id.hrsMinutes);
        uRl = findViewById(R.id.upperRL);
        tRl = findViewById(R.id.topRl);


        final List<String> numberList = new ArrayList<>(Arrays.asList(number_of_times));

        numberOfTimes.setEnabled(false);
        timeAt.setEnabled(true);
        tRl.setEnabled(false);
        frequency.setEnabled(false);

        final ArrayAdapter<String> numberList_arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, numberList) {
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        numberList_arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        numberOfTimes.setAdapter(numberList_arrayAdapter);

        numberOfTimes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position)+" Times";
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final RadioButton rb1 = findViewById(R.id.rb1);
        final RadioButton rb2 = findViewById(R.id.rb2);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    checkBox.toggle();
                }
                numberOfTimes.setEnabled(true);
                timeAt.setEnabled(false);
                frequency.setEnabled(false);
                rb1.setTextColor(Color.BLACK);
                rb2.setTextColor(Color.BLACK);
                waterFrom.setTextColor(Color.BLACK);
                waterTo.setTextColor(Color.BLACK);
                timeFrom.setHintTextColor(Color.parseColor("#2Ac9c9"));
                timeTo.setHintTextColor(Color.parseColor("#2Ac9c9"));
                timeAt.setHintTextColor(Color.parseColor("#cccccc"));
                checkBox.setTextColor(Color.parseColor("#cccccc"));
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    checkBox.toggle();
                }
                numberOfTimes.setEnabled(false);
                timeAt.setEnabled(false);
                frequency.setEnabled(true);
                rb2.setTextColor(Color.BLACK);
                rb1.setTextColor(Color.BLACK);
                waterFrom.setTextColor(Color.BLACK);
                waterTo.setTextColor(Color.BLACK);
                timeFrom.setHintTextColor(Color.parseColor("#2Ac9c9"));
                timeTo.setHintTextColor(Color.parseColor("#2Ac9c9"));
                timeAt.setHintTextColor(Color.parseColor("#cccccc"));
                checkBox.setTextColor(Color.parseColor("#cccccc"));
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() != -1){
                    radioGroup.clearCheck();
                }
                numberOfTimes.setEnabled(false);
                timeAt.setEnabled(true);
                tRl.setEnabled(false);
                frequency.setEnabled(false);
                checkBox.setTextColor(Color.BLACK);
                waterFrom.setTextColor(Color.parseColor("#cccccc"));
                waterTo.setTextColor(Color.parseColor("#cccccc"));
                timeFrom.setHintTextColor(Color.parseColor("#cccccc"));
                timeTo.setHintTextColor(Color.parseColor("#cccccc"));
                timeAt.setHintTextColor(Color.parseColor("#2Ac9c9"));
                rb1.setTextColor(Color.parseColor("#cccccc"));
                rb2.setTextColor(Color.parseColor("#cccccc"));
            }
        });


        final List<String> frequencyList = new ArrayList<>(Arrays.asList(frequencies));

        final ArrayAdapter<String> frequencyList_arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, frequencyList) {
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        frequencyList_arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        frequency.setAdapter(frequencyList_arrayAdapter);

        frequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        line1=findViewById(R.id.lineView);
//        line2=findViewById(R.id.lineView2);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width/2-8, 1);
//        line1.setLayoutParams(layoutParams);


        timeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ifrom = new Intent(waterReminder.this,systemClock.class);
                startActivityForResult(ifrom,1);
            }
        });

        timeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ito = new Intent(waterReminder.this,systemClock.class);
                startActivityForResult(ito,2);
            }
        });
        timeAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iat = new Intent(waterReminder.this,systemClock.class);
                startActivityForResult(iat,3);
            }
        });
    }
    private Dialog b1()
    {
        final String[] items = {
                "Item 1",
                "Item2",};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hi this is a spinner");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch(item) {
                    case 0:{
                        /* Item 1 */ break;}
                    case 1:{
                        /* Item 2 */break;}
                }
            }
        });

        return builder.create();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){
            String receivedMessage = data.getStringExtra("Data");
            timeFrom.setText(receivedMessage);
        }
        if (requestCode==2 && resultCode==RESULT_OK){
            String receivedMessage = data.getStringExtra("Data");
            timeTo.setText(receivedMessage);
        }
        if (requestCode==3 && resultCode==RESULT_OK) {
            String receivedMessage = data.getStringExtra("Data");
            timeAt.setText(receivedMessage);
        }
    }
}
