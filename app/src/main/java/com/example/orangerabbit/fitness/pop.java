package com.example.orangerabbit.fitness;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.orangerabbit.fitness.activity_water;


import com.example.orangerabbit.fitness.WaterFacts.waterFacts;

import java.util.Random;

public class pop extends Activity {

    TextView factVerse;
    public static final Random randomFact = new Random();
   // activity_water aw = new activity_water();

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

        getWindow().setLayout((int)(width*0.6),(int)(height*0.3));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM|Gravity.END;
        params.x = 300;
        params.y = 150;

        getWindow().setAttributes(params);

        factVerse = findViewById(R.id.facts_verse);
        factVerse.setText(pop.displayFacts());

    }


}




























