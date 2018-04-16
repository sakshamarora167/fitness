package com.example.orangerabbit.fitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {
    EditText editPhone,editPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editPassword=findViewById(R.id.editPassword);
        editPhone=findViewById(R.id.editPhone);
        btnSignIn=findViewById(R.id.btnSignIn);

        //Put fireBase code here
        //******//

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
