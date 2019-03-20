package com.example.restaurant;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    ArrayAdapter catAdapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        //set listener on listview
        list = findViewById(R.id.listView);
        ListviewListener OnListClick = new ListviewListener();
        list.setOnItemClickListener(OnListClick);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        //set adapter on listview
        catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        list.setAdapter(catAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class ListviewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //get category from clicked item
            String clickedCategory = (String) parent.getItemAtPosition(position);
            //set intent
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", clickedCategory);
            //open menu page
            intent.putExtras(intent);
            startActivity(intent);
        }
    }
}
