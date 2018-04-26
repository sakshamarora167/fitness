package com.example.orangerabbit.fitness;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button HpgSignUpButton,HpgLogInButton;
    TextView HpgSlogan,HpgAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HpgSignUpButton=findViewById(R.id.HpgSignUpButton);
        HpgLogInButton=findViewById(R.id.HpgLogInButton);

        HpgSlogan = findViewById(R.id.txtSlogan);
        HpgAppName = findViewById(R.id.txtName);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Fonts3.ttf");
        //HpgAppName.setTypeface(null, Typeface.BOLD_ITALIC);
        //HpgAppName.setTypeface(face);
        //HpgSlogan.getBackground().setAlpha(51);
        HpgSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        HpgSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });
        HpgLogInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent logIn = new Intent(MainActivity.this,LogIn.class);
                startActivity(logIn);
            }
        });

    }
}
