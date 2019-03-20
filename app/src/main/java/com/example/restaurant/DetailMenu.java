package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailMenu extends AppCompatActivity {

    String url;
    String name;
    String description;
    String price;
    ImageView detailImageView;
    TextView detailName;
    TextView detailDescription;
    TextView detailPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        //get intent
        Intent intent = getIntent();
        final MenuItem clickedItem = (MenuItem)intent.getSerializableExtra("item");

        //get image, name, description & price
        url = clickedItem.getImageUrl();
        name = clickedItem.getName();
        description = clickedItem.getDescription();
        price = String.valueOf(clickedItem.getPrice());

        //set appropriate views
        detailImageView = findViewById(R.id.detailImageView);
        detailName = findViewById(R.id.detailName);
        detailDescription = findViewById(R.id.detailDescription);
        detailPrice = findViewById(R.id.detailPrice);

        //set image using picasso
        Picasso.get().load(url).into(detailImageView);
        //set other values
        detailName.setText(name);
        detailDescription.setText(description);
        detailPrice.setText(price);







    }
}
