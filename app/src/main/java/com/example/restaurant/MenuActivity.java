package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemRequest.Callback {
    String menuChoice;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //get intent
        Intent intent = getIntent();
        menuChoice = intent.getStringExtra("category");
        MenuItemRequest x = new MenuItemRequest(this, menuChoice);
        x.getItems(this);
        list = findViewById(R.id.menuListView);
        //set listener
        ListviewListener OnListClick = new ListviewListener();
        list.setOnItemClickListener(OnListClick);


    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuList) {
        //set adapter
        ArrayAdapter menuAdapter =  new MenuItemAdapter(this, R.layout.activity_menu_item_adapter, menuList);
        list.setAdapter(menuAdapter);
    }

    @Override
    public void gotMenuItemsError(String message) {
    }

    private class ListviewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //get menuitem from clicked item
            MenuItem clickedItem = (MenuItem) parent.getItemAtPosition(position);
            //set intent
            Intent intent = new Intent(MenuActivity.this, DetailMenu.class);
            intent.putExtra("item", clickedItem);
            //open menu page
            intent.putExtras(intent);
            startActivity(intent);

        }
    }
}
