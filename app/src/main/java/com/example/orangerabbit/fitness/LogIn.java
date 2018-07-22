package com.example.orangerabbit.fitness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orangerabbit.fitness.Common.Common;
import com.example.orangerabbit.fitness.Model.User;
import com.fasterxml.jackson.databind.node.IntNode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {
    private EditText editPhone,editPassword;
    UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

//        setContentView(R.layout.activity_main);
//        Common.sp = getSharedPreferences("login",MODE_PRIVATE);
//
//        if(Common.sp.getBoolean("logged",false)){
//            goToMainActivity();
//        }

//        session = new UserSessionManager(getApplicationContext());

        editPassword=findViewById(R.id.editPassword);
        editPhone=findViewById(R.id.editPhone);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        session = new UserSessionManager(getApplicationContext());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(LogIn.this);
                mDialog.setMessage("Please wait..");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(editPhone.getText().toString()).exists()){
                        mDialog.dismiss();
                        User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            if (user != null) {
                                if(editPhone.getText().toString().equals("") || editPassword.getText().toString().equals("")){
                                    Toast.makeText(LogIn.this,"Fill the details please",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    if (user.getPassword().equals(editPassword.getText().toString())) {
                                        session.createUserLoginSession(editPassword.getText().toString(), editPhone.getText().toString());
                                        Intent intentHome = new Intent(LogIn.this, Home.class);
                                        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Common.currentUser = user;
                                        finish();
                                        startActivity(intentHome);
                                        //                                    Common.sp.edit().putBoolean("logged",true).apply();
                                    } else {
                                        Toast.makeText(LogIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }else{
                            mDialog.dismiss();
                            Toast.makeText(LogIn.this,"User does not exist in Database",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });



    }
    public void goToMainActivity(){
        Intent i = new Intent(this,Home.class);
        startActivity(i);
    }

}
