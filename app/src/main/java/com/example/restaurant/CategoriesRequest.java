package com.example.restaurant;
import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private JSONArray categories;
    private Callback activity;

    @Override
    public void onErrorResponse(VolleyError error) {
        getCategories(activity);
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<String> catlist = new ArrayList<>();
        //create empty JSONArray
        categories = new JSONArray();
        try {
            //load array into empty JSONArray
            categories = response.getJSONArray("categories");
            //iterate over categories
            for (int i = 0; i < categories.length(); i++) {
                catlist.add(categories.getString(i));
            }
            activity.gotCategories(catlist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void getCategories(Callback activity){
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( "https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }
}

