package com.example.orangerabbit.fitness;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import com.example.orangerabbit.fitness.WaterFacts.waterFacts;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//import java.util.Random;

import java.util.Locale;

import me.itangqi.waveloadingview.WaveLoadingView;

public class activity_water extends AppCompatActivity {

    TextView WaterIntake,ConsumedGlasses;
    ImageButton ellipsis,addGlass,subGlass;
    FloatingActionButton Facts;
    WaveLoadingView Water_percentage;
    SeekBar seekBar;
  //  private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Facts = findViewById(R.id.fab);
        Facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(activity_water.this,pop.class));
            }
        });



        ellipsis = findViewById(R.id.ellipsis);
        addGlass = findViewById(R.id.addGlass);
        subGlass = findViewById(R.id.subtractGlass);

        ConsumedGlasses = (TextView)findViewById(R.id.consumedGlasses);

        ellipsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(activity_water.this,ellipsis);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(activity_water.this,"Will remind at Noon",Toast.LENGTH_LONG).show();
                        return  true;
                    }
                });
                popupMenu.show();
            }
        });

        seekBar = findViewById(R.id.seekBar);
        Water_percentage = findViewById(R.id.WaveLoadingView);
        Water_percentage.setProgressValue(0);
        seekBar.setProgress(0);
        //seekBar.incrementProgressBy(10);
        seekBar.setMax(90);
        seekBar.setVisibility(View.INVISIBLE);
        ConsumedGlasses.setText("0");

        addGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seekBar.getProgress() < 90){
                    seekBar.incrementProgressBy(10);
                }
            }
        });

        subGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seekBar.getProgress() > 0){
                    seekBar.incrementProgressBy(-10);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Water_percentage.setProgressValue(progress);
//                if (progress <50) {
 //               if (progress == progress + 10) {
                    progress=progress/10;
//                    count += 1;
                    ConsumedGlasses.setText(String.valueOf(progress));
                   // progress = count;
//                    Water_percentage.setBottomTitle(String.format(Locale.getDefault(),"%d%%",progress));
//                    Water_percentage.setCenterTitle("");
//                    Water_percentage.setTopTitle("");
//                }
//                else if(progress<80){
                    Water_percentage.setBottomTitle("");
                    Water_percentage.setCenterTitle(String.format(Locale.getDefault(),"%.1f%%",progress*((float)100/(float)9)));
                    Water_percentage.setTopTitle("");
//                }
//                else{
//                    Water_percentage.setBottomTitle("");
//                    Water_percentage.setCenterTitle("");
//                    Water_percentage.setTopTitle(String.format(Locale.getDefault(),"%d%%",progress));
//                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void showPopup(View view) {
    }

}
