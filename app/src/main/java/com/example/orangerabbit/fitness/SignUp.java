package com.example.orangerabbit.fitness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orangerabbit.fitness.Common.Common;
import com.example.orangerabbit.fitness.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText editPhone,editName,editPassword;
    Button btnSignUp;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        editPassword = findViewById(R.id.editPassword);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSignUp = findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editPhone.getText().toString().length() != 10 ){
                    editPhone.setError("number is incorrect");
                }else if(editPassword.getText().toString().isEmpty()){
                    editPassword.setError("input password");
                }else{

                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("Registering Please wait..");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.child(editPhone.getText().toString()).exists()) {
                                firebaseAuth.signInWithCustomToken(editPhone.getText().toString()).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            mDialog.dismiss();
                                            User user = new User(editName.getText().toString(), editPassword.getText().toString());
                                            //table_user.child(editPhone.getText().toString()).setValue(user);
                                            //Toast.makeText(SignUp.this,"Welcome "+editName.getText().toString(),Toast.LENGTH_SHORT).show();
                                            Intent intentHome = new Intent(SignUp.this,Home.class);
                                            Common.currentUser = user;
                                            startActivity(intentHome);
                                            finish();
                                        }
                                    }
                                });

                            }
                            else
                                {
                                    mDialog.dismiss();
                                    Toast.makeText(SignUp.this, "Phone number already exists", Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

}
