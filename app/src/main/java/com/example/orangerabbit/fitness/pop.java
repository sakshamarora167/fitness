package com.example.orangerabbit.fitness;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.orangerabbit.fitness.WaterFacts.waterFacts;

import java.util.Random;

public class pop extends Activity {

    TextView factVerse;
    public static final Random randomFact = new Random();

    public static String displayFacts(){
        int factsLength = waterFacts.getHELIOS().length;
        return waterFacts.getHELIOS()[randomFact.nextInt(factsLength)];
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

//        LayoutInflater inflater = (LayoutInflater) pop.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View layout = inflater.inflate(R.layout.popwindow, null,false);
////
//
//        ratePw = new PopupWindow(layout);
//        ratePw.setWidth(width-20);
//        ratePw.setHeight(height-20);
//        ratePw.setFocusable(true);

        getWindow().setLayout((int)(width*0.8),(int)(height*0.2));
       // getWindow().
        //getWindow().setAttributes();

        factVerse = findViewById(R.id.facts_verse);
        factVerse.setText(pop.displayFacts());

    }

}
