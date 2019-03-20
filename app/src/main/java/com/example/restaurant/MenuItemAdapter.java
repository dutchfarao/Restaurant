package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    private ArrayList<MenuItem> menuItems;
        String price;

    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_menu_item_adapter, parent, false);
        }
        //set appropriate views
        ImageView menuImageView = convertView.findViewById(R.id.menuImageView);
        TextView menuName = convertView.findViewById(R.id.menuName);
        TextView menuPrice = convertView.findViewById(R.id.menuPrice);
        MenuItem chooser = menuItems.get(position);
        //set image using picasso
        String url;
        url = chooser.getImageUrl();
        Picasso.get().load(url).into(menuImageView);

        //set name of dish
        menuName.setText(chooser.getName());

        //set price of dish
        price = String.valueOf(chooser.getPrice());
        menuPrice.setText(price);
        //return
        return convertView;
    }

}
