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
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.adapter.AdapterListSeminar;
import com.example.myapplication.adapter.ExpandableAdapter;
import com.example.myapplication.api.MyApi;
import com.example.myapplication.model.ListSeminar;
import com.example.myapplication.model.MenuModel;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewSeminarActivity extends AppCompatActivity {


    String[] ItemNames4 = {"سانس", "11:30 تا 13:30", "13:30 تا 15:30", "15:30 تا 17:30", "17:30 تا 19:30"};
//    Spinner sp4;
//    Button add_sans;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView img_drawer;


    ExpandableAdapter expandableAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerlist = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childlist = new HashMap<>();

    RecyclerView recyclerView;
    List<ListSeminar> seminars1;
    //    private static String JSON_URL = "https://hasanlo.ir/android/nilofar/recyclerwithvolley/data.json";
    AdapterListSeminar adapter;
    Retrofit retrofit;

    TextView txt_sabt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);
        
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://hasanlo.ir")
                .build();

        MyApi myApi = retrofit.create(MyApi.class);
        myApi.getSeminars1().enqueue(new Callback<List<ListSeminar>>() {
            @Override
            public void onResponse(Call<List<ListSeminar>> call, retrofit2.Response<List<ListSeminar>> response) {
                List<ListSeminar> seminars = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new AdapterListSeminar(getApplicationContext(), seminars1);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListSeminar>> call, Throwable t) {

            }
        });

        recyclerView = findViewById(R.id.seminarsList1);
        seminars1 = new ArrayList<>();

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

        TextView txt_sabt = (TextView) findViewById(R.id.txt_sabt);
        txt_sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSeminarActivity.this, AddSansActivity.class);
                startActivity(intent);
            }
        });

//        Button add_sans = (Button)findViewById(R.id.add_sans);
//        add_sans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewSeminarActivity.this, AddSansActivity.class);
//                startActivity(intent);
//            }
//        });

//        Spinner sp4 =(Spinner)findViewById(R.id.spinner4);
//        ArrayAdapter<String> valueAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, ItemNames4);
//        valueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp4.setAdapter(valueAdapter);

        init();
    }

    private void populateExpandableListView() {

        expandableAdapter = new ExpandableAdapter(this, headerlist, childlist);
        expandableListView.setAdapter(expandableAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

                if (groupPosition == 0) {
                    Toast.makeText(ViewSeminarActivity.this, "ورود به پروفایل", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewSeminarActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (groupPosition == 1) {

                } else if (groupPosition == 2) {

                } else if (groupPosition == 3) {

                } else if (groupPosition == 4) {
                    Toast.makeText(ViewSeminarActivity.this, "سیاست مالی", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewSeminarActivity.this, PolicyActivity.class);
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
                        Intent intent1 = new Intent(ViewSeminarActivity.this, AddSeminarActivity.class);
                        startActivity(intent1);
                    }
                    if (childPosition == 1) {
                        Toast.makeText(ViewSeminarActivity.this, "ثبت سانس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(ViewSeminarActivity.this, AddSansActivity.class);
                        startActivity(intent2);
                    }
                    if (childPosition == 2) {
                        Toast.makeText(ViewSeminarActivity.this, "ثبت آدرس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(ViewSeminarActivity.this, AddressActivity.class);
                        startActivity(intent3);
                    }
                } else if (groupPosition == 3) {
                    if (childPosition == 0)
                        Toast.makeText(ViewSeminarActivity.this, "افزودن مشترک", Toast.LENGTH_SHORT).show();
                } else if (groupPosition == 2) {
                    if (childPosition == 0)
                        Toast.makeText(ViewSeminarActivity.this, "لیست شماره دعوتی", Toast.LENGTH_SHORT).show();
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

    public void init() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        img_drawer = findViewById(R.id.img_drawer);
        txt_sabt = findViewById(R.id.txt_sabt);
//        sp4 = findViewById(R.id.spinner4);
//        add_sans = findViewById(R.id.add_sans);
//        recyclerView1 = findViewById(R.id.seminarsList1);
    }
}