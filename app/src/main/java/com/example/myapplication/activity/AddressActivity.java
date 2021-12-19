package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapter.AdapterAddress;
import com.example.myapplication.adapter.ExpandableAdapter;
import com.example.myapplication.model.Address;
import com.example.myapplication.model.MenuModel;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddressActivity extends AppCompatActivity {


    Button button_add_address;
    RecyclerView recyclerView;
    List<Address> address;
    AdapterAddress adapterAddress;
    private static String JSON_URL = "https://hasanlo.ir/android/nilofar/recyclerwithvolley/seminar.json";

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView img_drawer;

    ExpandableAdapter expandableAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerlist = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childlist = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainaddress);


        init();

        recyclerView = findViewById(R.id.recyclerView);
        address = new ArrayList<>();

        extractAddres();



        button_add_address = findViewById(R.id.button_add_address);
        button_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, AddNewAddressActivity.class);
                startActivity(intent);
            }
        });

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
                    Toast.makeText(AddressActivity.this, "ورود به پروفایل", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddressActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (groupPosition == 1) {

                } else if (groupPosition == 2) {

                } else if (groupPosition == 3) {

                } else if (groupPosition == 4) {
                    Toast.makeText(AddressActivity.this, "سیاست مالی", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddressActivity.this, PolicyActivity.class);
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
                        Intent intent1 = new Intent(AddressActivity.this, AddSeminarActivity.class);
                        startActivity(intent1);
                    }
                    if (childPosition == 1) {
                        Toast.makeText(AddressActivity.this, "ثبت سانس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(AddressActivity.this, AddSansActivity.class);
                        startActivity(intent2);
                    }
                    if (childPosition == 2) {
                        Toast.makeText(AddressActivity.this, "ثبت آدرس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(AddressActivity.this, AddressActivity.class);
                        startActivity(intent3);
                    }
                } else if (groupPosition == 3) {
                    if (childPosition == 0)
                        Toast.makeText(AddressActivity.this, "افزودن مشترک", Toast.LENGTH_SHORT).show();
                } else if (groupPosition == 2) {
                    if (childPosition == 0)
                        Toast.makeText(AddressActivity.this, "لیست شماره دعوتی", Toast.LENGTH_SHORT).show();
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
        childModelList.add(childModel);
        childModel = new MenuModel("ثبت سانس سینار", false, false);
        childModelList.add(childModel);
        childModel = new MenuModel("ثبت آدرس سمینار", false, false);
        childModelList.add(childModel);
        childModel = new MenuModel("گزارشات سمینار", false, false);
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

    private void extractAddres() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject adressObject = response.getJSONObject(i);

                        Address address1 = new Address();
                        address1.setAddress(adressObject.getString("addressSeminar").toString());
                        address.add(address1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                adapterAddress = new AdapterAddress(getApplicationContext(), address);
                recyclerView.setAdapter(adapterAddress);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }

    public void init() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        img_drawer = findViewById(R.id.img_drawer);
    }
}
