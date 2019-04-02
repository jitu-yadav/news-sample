package com.example.demo.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // news json url
    private static final String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=5418401c81514540bdf03d13fbad7171";
    private ProgressDialog pDialog;
    private List<News> newslist = new ArrayList<News>();
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new NewsListAdapter(this, newslist);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsDetailsActivity.class);
                intent.putExtra("selectedNews", view.getTag().toString());
                startActivity(intent);
            }
        });


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("TAG", response);
                        hidePDialog();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = null;

                            if (jsonObject.getString("status").equalsIgnoreCase("ok")) {

                                jsonArray = jsonObject.getJSONArray("articles");
                                // Parsing json
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {

                                        JSONObject obj = jsonArray.getJSONObject(i);
                                        News news = new News();
                                        news.setNewsTitle(obj.getString("title"));
                                        news.setThumbnailUrl(obj.getString("urlToImage"));
                                        news.setNewsDetails(obj.getString("description"));
                                        news.setNewsDetailsUrl(obj.getString("url"));

                                        // adding news to newslist array
                                        newslist.add(news);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
