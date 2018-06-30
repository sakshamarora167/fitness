package com.example.orangerabbit.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.orangerabbit.fitness.Common.Common;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button HpgSignUpButton,HpgLogInButton;
    TextView HpgSlogan,HpgAppName;
    UserSessionManager session;
    private FirebaseAuth firebaseAuth;

   // private static final int PERMISSION_REQUEST_CODE = 1;
    private String TAG;
    //Window window = MainActivity.getWindow();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isStoragePermissionGranted();
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            Intent i = new Intent(this,Home.class);
            startActivity(i);
        }

//        session = new UserSessionManager(getApplicationContext());
//
//        if (session.isUserLoggedIn()){
//
//            startActivity(new Intent(this, Home.class));
//        }

        HpgSignUpButton=findViewById(R.id.HpgSignUpButton);
        HpgLogInButton=findViewById(R.id.HpgLogInButton);

        HpgSlogan = findViewById(R.id.txtSlogan);
        HpgAppName = findViewById(R.id.txtName);
  //      Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Fonts3.ttf");
        //HpgAppName.setTypeface(null, Typeface.BOLD_ITALIC);
        //HpgAppName.setTypeface(face);
        //HpgSlogan.getBackground().setAlpha(51);

        HpgSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
                finish();
            }
        });
        HpgLogInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent logIn = new Intent(MainActivity.this,LogIn.class);
                startActivity(logIn);
                finish();
            }
        });

//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setStatusBarColor(MainActivity.getResources().getColor(R.color.example_color));

    }

    public boolean isStoragePermissionGranted(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
}
