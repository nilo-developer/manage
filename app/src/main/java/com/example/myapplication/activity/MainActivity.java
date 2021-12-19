package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.api.MyApi;
import com.example.myapplication.model.MenuModel;
import com.example.myapplication.R;
import com.example.myapplication.model.Seminar;
import com.example.myapplication.adapter.Adapter;
import com.example.myapplication.adapter.ExpandableAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Seminar> seminars;
//    private static String JSON_URL = "https://hasanlo.ir/android/nilofar/recyclerwithvolley/data.json";
    Adapter adapter;
    Retrofit retrofit;


    ExpandableAdapter expandableAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerlist = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childlist = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://hasanlo.ir")
                .build();

        MyApi myApi = retrofit.create(MyApi.class);
        myApi.getSeminars().enqueue(new Callback<List<Seminar>>() {
            @Override
            public void onResponse(Call<List<Seminar>> call, retrofit2.Response<List<Seminar>> response) {
                List<Seminar> seminars = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), seminars);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Seminar>> call, Throwable t) {

            }
        });

        recyclerView = findViewById(R.id.seminarsList);
        seminars = new ArrayList<>();
//        extractSeminars();

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView img_drawer = (ImageView) findViewById(R.id.img_drawer);
        img_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        expandableListView = findViewById(R.id.expandablelistview);

        GetDataNavigation();
        populateExpandableListView();

    }

    private void populateExpandableListView() {

        expandableAdapter = new ExpandableAdapter(this, headerlist, childlist);
        expandableListView.setAdapter(expandableAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

                if (groupPosition == 0) {
                    Toast.makeText(MainActivity.this, "ورود به پروفایل", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (groupPosition == 1) {

                } else if (groupPosition == 2) {

                } else if (groupPosition == 3) {

                } else if (groupPosition == 4) {
                    Toast.makeText(MainActivity.this, "سیاست مالی", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, PolicyActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

                if (groupPosition == 1) {
                    if (childPosition == 0) {
                        Intent intent1 = new Intent(MainActivity.this, AddSeminarActivity.class);
                        startActivity(intent1);
                    }
                    if (childPosition == 1) {
                        Toast.makeText(MainActivity.this, "ثبت سانس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(MainActivity.this, AddSansActivity.class);
                        startActivity(intent2);
                    }
                    if (childPosition == 2) {
                        Toast.makeText(MainActivity.this, "ثبت آدرس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(MainActivity.this, AddressActivity.class);
                        startActivity(intent3);
                    }
                } else if (groupPosition == 3) {
                    if (childPosition == 0) {
                        Intent intent4 = new Intent(MainActivity.this, AddCommonActivity.class);
                        startActivity(intent4);
                    }
                    if (childPosition == 1) {
                        Intent intent5 = new Intent(MainActivity.this, ListCommonActivity.class);
                        startActivity(intent5);
                    }
                } else if (groupPosition == 2) {
                    if (childPosition == 0)
                        Toast.makeText(MainActivity.this, "لیست شماره دعوتی", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private void GetDataNavigation() {
        MenuModel menuModel = new MenuModel("ورود به پروفایل", false, true);
        headerlist.add(menuModel);

        if (!menuModel.hasChildren) {
            childlist.put(menuModel, null);
        }

        menuModel = new MenuModel(" سمینار", true, true);
        headerlist.add(menuModel);
        List<MenuModel> childModelList = new ArrayList<>();
        MenuModel childModel = new MenuModel("ثبت  سمینار جدید", false, false);
//        childModelList.add(childModel);
//        childModel = new MenuModel("ثبت سانس سینار", false, false);
        childModelList.add(childModel);
        childModel = new MenuModel("ثبت سانس سمینار", false, false);
        childModelList.add(childModel);
        childModel = new MenuModel("ثبت آدرس سمینار", false, false);
        childModelList.add(childModel);

        if (menuModel.hasChildren) {
            childlist.put(menuModel, childModelList);
        }

        menuModel = new MenuModel("ثبت شماره دعوتی", true, true);
        headerlist.add(menuModel);
        childModelList = new ArrayList<>();
        childModel = new MenuModel(" لیست شماره دعوتی", false, false);
        childModelList.add(childModel);

        if (menuModel.hasChildren) {
            childlist.put(menuModel, childModelList);
        }

        menuModel = new MenuModel("مشترکین", true, true);
        headerlist.add(menuModel);
        childModelList = new ArrayList<>();
        childModel = new MenuModel("افزودن مشترک", false, false);
        childModelList.add(childModel);
        childModel = new MenuModel("لیست مشترکین", false, false);
        childModelList.add(childModel);

        if (menuModel.hasChildren) {
            childlist.put(menuModel, childModelList);
        }

        menuModel = new MenuModel("سیاست مالی", false, true);
        headerlist.add(menuModel);

        if (!menuModel.hasChildren) {
            childlist.put(menuModel, null);
        }
    }

    @Override
    public void onBackPressed() {
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

//    private void extractSeminars() {
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject seminarObject = response.getJSONObject(i);
//
//                        Seminar seminar = new Seminar();
//                        seminar.setTitle(seminarObject.getString("title").toString());
//                        seminar.setCity(seminarObject.getString("city"));
//                        seminar.setLocation(seminarObject.getString("location").toString());
//                        seminar.setType(seminarObject.getString("type").toString());
//                        seminar.setDay(seminarObject.getString("day").toString());
//                        seminars.add(seminar);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapter = new Adapter(getApplicationContext(), seminars);
//                recyclerView.setAdapter(adapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("tag", "onErrorResponse: " + error.getMessage());
//            }
//        });
//
//        queue.add(jsonArrayRequest);
//
//    }

}