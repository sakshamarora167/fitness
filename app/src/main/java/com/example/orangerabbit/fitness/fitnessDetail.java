package com.example.orangerabbit.fitness;

import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.orangerabbit.fitness.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import in.goodiebag.carouselpicker.CarouselPicker;

public class fitnessDetail extends AppCompatActivity {

//    TextView foodName,foodPrice;
    TextView food_proteins,food_carbs,food_fiber,food_fats;
    TextView proteins_data,fat_data,carbs_data,fiber_data;
    ImageView foodImage,proteinImage,carbsImage,fiberImage,fatImage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CarouselPicker food_container,food_amount;
    FloatingActionButton btnCart;

    String food_id="";

    FirebaseDatabase database;
    DatabaseReference ref;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Food");

        //Init View
        food_container = (CarouselPicker) findViewById(R.id.carouselPicker);
        food_amount = (CarouselPicker) findViewById(R.id.carouselPicker2);

        List<CarouselPicker.PickerItem> containerItems = new ArrayList<>();
        containerItems.add(new CarouselPicker.TextItem("Bowl",12));
        containerItems.add(new CarouselPicker.TextItem("Plate",12));
        containerItems.add(new CarouselPicker.TextItem("Grams",12));
        CarouselPicker.CarouselViewAdapter containerAdapter = new CarouselPicker.CarouselViewAdapter(this,containerItems,0);
        food_container.setAdapter(containerAdapter);

        List<CarouselPicker.PickerItem> textAmount = new ArrayList<>();
        textAmount.add(new CarouselPicker.TextItem("1",13));
        textAmount.add(new CarouselPicker.TextItem("2",13));
        textAmount.add(new CarouselPicker.TextItem("3",13));
        textAmount.add(new CarouselPicker.TextItem("4",13));
        textAmount.add(new CarouselPicker.TextItem("5",13));
        textAmount.add(new CarouselPicker.TextItem("6",13));
        textAmount.add(new CarouselPicker.TextItem("7",13));
        CarouselPicker.CarouselViewAdapter amountAdapter = new CarouselPicker.CarouselViewAdapter(this,textAmount,0);
        food_amount.setAdapter(amountAdapter);

        food_amount.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("Carousel Try","position : "+position);
                proteins_data.setText(String.format(Locale.US,"%.1f",(2.8*(position+1)))+"g");
                carbs_data.setText(String.format(Locale.US,"%.1f",11.9*(position+1))+"g");
                fat_data.setText(String.format(Locale.US,"%.1f",6.5*(position+1))+"g");
                fiber_data.setText(String.format(Locale.US,"%.1f",1.7*(position+1))+"g");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnCart = (FloatingActionButton)findViewById(R.id.Add_btn);

        food_proteins = (TextView)findViewById(R.id.food_proteins);
        food_carbs = (TextView)findViewById(R.id.food_carbs);
        food_fats = (TextView)findViewById(R.id.food_fats);
        food_fiber = (TextView)findViewById(R.id.food_fiber);

        proteins_data = (TextView)findViewById(R.id.proteins_data);
        carbs_data = (TextView)findViewById(R.id.carbs_data);
        fat_data = (TextView)findViewById(R.id.fats_data);
        fiber_data = (TextView)findViewById(R.id.fiber_data);

        proteins_data.setText("2.8g");
        carbs_data.setText("11.9g");
        fat_data.setText("6.5g");
        fiber_data.setText("1.7g");

        foodImage = findViewById(R.id.img_fitness);
        //foodName = findViewById(R.id.foo);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //get food id from intent
        if(getIntent() != null)
            food_id = getIntent().getStringExtra("FoodId");
        if(!food_id.isEmpty()){
            getDetailFood(food_id);
        }

    }

    private void getDetailFood(String food_id) {
        ref.child(food_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);

                Picasso.with(getBaseContext()).load(food.getImage())            //Set Image
                        .into(foodImage);

                collapsingToolbarLayout.setTitle(food.getName());

                //foodName.setText(food.getName());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}








































