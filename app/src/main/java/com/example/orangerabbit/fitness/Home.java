package com.example.orangerabbit.fitness;

import android.app.ProgressDialog;
import android.content.Intent;
//import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
//import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orangerabbit.fitness.Common.Common;
import com.example.orangerabbit.fitness.Interface.ItemClickListener;
import com.example.orangerabbit.fitness.Model.Category;
//import com.example.orangerabbit.fitness.Model.User;
import com.example.orangerabbit.fitness.ViewHolder.FitnessViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

//import static com.example.orangerabbit.fitness.R.layout.fitness_item;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textFullName;
    RecyclerView recycler_fitness;
    RecyclerView.LayoutManager layoutManager;

    String WelcomeName;
    View headerView;
    FirebaseDatabase database;
    DatabaseReference category;
    Query fitnessQuery;
    DatabaseReference fitnessRef;
    FirebaseRecyclerAdapter<Category,FitnessViewHolder> adapter;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //HashMap<String,String> user = session.getuserDetails();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Items");
        category.keepSynced(true);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set user's full name
        headerView = navigationView.getHeaderView(0);
        textFullName = headerView.findViewById(R.id.txtFullName);
        WelcomeName =  Common.currentUser.getName();
        textFullName.setText(Common.currentUser.getName());

        //Load attributes
        recycler_fitness = findViewById(R.id.recycler_menu);
        recycler_fitness.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_fitness.setLayoutManager(layoutManager);


        fitnessRef = FirebaseDatabase.getInstance().getReference().child("Items");
        fitnessQuery = fitnessRef.orderByKey();
        loadAttribute();

//        if(session.checkLogin()){
//            finish();
//        }

//        HashMap<String,String> user = session.getuserDetails();
//        String name = user.get(UserSessionManager.KEY_NAME);
//
//        String number = user.get(UserSessionManager.KEY_NUMBER);
    }

    private void loadAttribute() {
        final ProgressDialog mDialog = new ProgressDialog(Home.this);
        mDialog.setMessage("Please wait..");
        mDialog.show();

        category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDialog.dismiss();
               // Category categ = dataSnapshot.child("01").getValue(Category.class);
                Toast.makeText(Home.this,"Welcome "+WelcomeName,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(fitnessQuery, Category.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Category, FitnessViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FitnessViewHolder holder, int position, @NonNull Category model) {
                //Log.w("Check","Model Name = "+model.getName());
                holder.txtFitnessName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(holder.imageView);
                final Category clickItem = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view , int position, boolean isLongClick) {
                        if(adapter.getRef(position).getKey().equals("01")) {
                            Intent foodList = new Intent(Home.this, FoodList.class);
                            foodList.putExtra("CategoryId", adapter.getRef(position).getKey());
                            startActivity(foodList);
                        }
                        else if(adapter.getRef(position).getKey().equals("02")) {
                            Intent waterConsumption = new Intent(Home.this, activity_water.class);
                            //foodList.putExtra("CategoryId", adapter.getRef(position).getKey());
                            startActivity(waterConsumption);
                        }
                        else{
                            Toast.makeText(Home.this," "+clickItem.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @NonNull
            @Override
            public FitnessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fitness_item, parent, false);

                return new FitnessViewHolder(view);
            }
        };
        adapter.startListening();
        recycler_fitness.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }else if (id == R.id.nav_signOut) {
            session = new UserSessionManager(getApplicationContext());
            session.logoutUser();
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
