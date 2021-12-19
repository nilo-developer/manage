package com.example.myapplication.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mtextfield.TextFieldBoxes;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ExpandableAdapter;
import com.example.myapplication.model.MenuModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddSeminarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    CheckBox simpleCheckBox1, simpleCheckBox2;

    DatePickerDialog datePickerDialog;
    TextView textViewdate;
//    String[] ItemNames1 = {"شهر", "تهران", "شیراز", "تبریز", "مشهد"};
//    String[] ItemNames2 = {"مکان برگزاری", "هتل ورزش", "هتل پارسیان", "هتل کوثر", "هتل ورزش"};

//    Spinner sp1, sp2;
    Button btn_sabt, btn_enseraf, glowButton3;

    MaterialSpinner spinner1,spinner2;

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
        setContentView(R.layout.main1);

        init();

        textViewdate = (TextView) findViewById(R.id.textViewdate);
//        imageButton = (ImageButton)findViewById(R.id.imageButton);
        textViewdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddSeminarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                textViewdate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
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

//
//        Spinner sp1 = (Spinner) findViewById(R.id.spinner1);
//        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
//        Spinner sp3 = (Spinner) findViewById(R.id.spinner3);

        MaterialSpinner spinner1 = (MaterialSpinner) findViewById(R.id.spinner1);
        spinner1.setItems("شهر", "تهران", "شیراز", "مشهد", "تبریز");
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        MaterialSpinner spinner2 = (MaterialSpinner)findViewById(R.id.spinner2);
        spinner2.setItems("مکان برگزاری","هتل پارسیان","هتل کوثر","هتل ورزش","هتل اسپیناس","هتل کوثر2");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

//        sp1.setOnItemSelectedListener(this);
//        sp2.setOnItemSelectedListener(this);
//        sp3.setOnItemSelectedListener(this);

//        ArrayAdapter<String> valueAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, ItemNames1);
//        valueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp1.setAdapter(valueAdapter);

//        valueAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, ItemNames2);
//        valueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp2.setAdapter(valueAdapter);

//        valueAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, ItemNames2);
//        valueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp2.setAdapter(valueAdapter);


        simpleCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    Toast.makeText(AddSeminarActivity.this, "حضوری", Toast.LENGTH_SHORT).show();
                    simpleCheckBox2.setChecked(false);
                }
            }
        });
        simpleCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    Toast.makeText(AddSeminarActivity.this, "آنلاین", Toast.LENGTH_SHORT).show();
                    simpleCheckBox1.setChecked(false);
                }
            }
        });

        btn_sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSeminarActivity.this, ViewSeminarActivity.class);
                startActivity(intent);
            }
        });


        final TextFieldBoxes textFieldBoxes = findViewById(R.id.text_field_boxes);
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        final TextFieldBoxes textFieldBoxes1 = findViewById(R.id.text_field_boxes1);
//        textFieldBoxes1.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });

        glowButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSeminarActivity.this, AddNewAddressActivity.class);
                startActivity(intent);
            }
        });

//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddSeminarActivity.this,AddDateActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    private void populateExpandableListView() {

        expandableAdapter = new ExpandableAdapter(this, headerlist, childlist);
        expandableListView.setAdapter(expandableAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

                if (groupPosition == 0) {
                    Toast.makeText(AddSeminarActivity.this, "ورود به پروفایل", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSeminarActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (groupPosition == 1) {

                } else if (groupPosition == 2) {

                } else if (groupPosition == 3) {

                } else if (groupPosition == 4) {
                    Toast.makeText(AddSeminarActivity.this, "سیاست مالی", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSeminarActivity.this, PolicyActivity.class);
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
                        Intent intent1 = new Intent(AddSeminarActivity.this, AddSeminarActivity.class);
                        startActivity(intent1);
                    }
                    if (childPosition == 1) {
                        Toast.makeText(AddSeminarActivity.this, "ثبت سانس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(AddSeminarActivity.this, AddSansActivity.class);
                        startActivity(intent2);
                    }
                    if (childPosition == 2) {
                        Toast.makeText(AddSeminarActivity.this, "ثبت آدرس سمینار", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(AddSeminarActivity.this, AddressActivity.class);
                        startActivity(intent3);
                    }
                } else if (groupPosition == 3) {
                    if (childPosition == 0)
                        Toast.makeText(AddSeminarActivity.this, "افزودن مشترک", Toast.LENGTH_SHORT).show();
                } else if (groupPosition == 2) {
                    if (childPosition == 0)
                        Toast.makeText(AddSeminarActivity.this, "لیست شماره دعوتی", Toast.LENGTH_SHORT).show();
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
        simpleCheckBox1 = findViewById(R.id.simpleCheckBox1);
        simpleCheckBox2 = findViewById(R.id.simpleCheckBox2);
        btn_enseraf = findViewById(R.id.btn_enseraf);
        btn_sabt = findViewById(R.id.btn_sabt);
//        sp1 = findViewById(R.id.spinner1);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 =findViewById(R.id.spinner2);
//        sp2 = findViewById(R.id.spinner2);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        img_drawer = findViewById(R.id.img_drawer);
        glowButton3 = findViewById(R.id.glowButton3);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}