package com.example.restaurant;

import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public Context context;
    public JSONArray items;
    Callback activity;
    public String name;
    public String description;
    public String imageUrl;
    public float price;
    public String category;
    public String menuChoice;

    @Override
    public void onErrorResponse(VolleyError error) {
        getItems(activity);
        activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> menulist = new ArrayList<>();
        //create empty JSONArray
        items = new JSONArray();
        try {
            //fill JSONArray with arraylist from url
            items = response.getJSONArray("items");
            //iterate over objects
            for (int i = 0; i < items.length(); i++) {
                JSONObject object = items.getJSONObject(i);
                //create menuItem object
                MenuItem menuItem = new MenuItem();
                //fill object with info
                name = object.getString("name");
                menuItem.setName(name);
                description = object.getString("description");
                menuItem.setDescription(description);
                imageUrl = object.getString("image_url");
                menuItem.setImageUrl(imageUrl);
                price = object.getInt("price");
                menuItem.setPrice(price);
                category = object.getString("category");
                menuItem.setCategory(category);
                //add filled object to arraylist
                menulist.add(menuItem);
            }
            activity.gotMenuItems(menulist);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void getItems(Callback activity){
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        //if user choses appetizers:
        if (menuChoice.equals("appetizers")){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category=appetizers", null, this, this);
            queue.add(jsonObjectRequest);
        }
        //if user choses entrees:
        else if(menuChoice.equals("entrees")) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category=entrees", null, this, this);
            queue.add(jsonObjectRequest);
        }
    }

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuList);
        void gotMenuItemsError(String message);
    }

    public MenuItemRequest(Context context, String choice) {
        this.context = context;
        menuChoice = choice;
    }
}



