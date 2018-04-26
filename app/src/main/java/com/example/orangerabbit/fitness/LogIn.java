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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {
    private EditText editPhone,editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editPassword=findViewById(R.id.editPassword);
        editPhone=findViewById(R.id.editPhone);
        Button btnSignIn = findViewById(R.id.btnSignIn);

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
                                if(user.getPassword().equals(editPassword.getText().toString())){
                                    Intent intentHome = new Intent(LogIn.this,Home.class);
                                    Common.currentUser = user;
                                    startActivity(intentHome);
                                    finish();
                                }
                                else{
                                    Toast.makeText(LogIn.this,"Sign in failed",Toast.LENGTH_SHORT).show();
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
}
