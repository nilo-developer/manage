package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.adapter.AdapterListCommon;
import com.example.myapplication.api.MyApi;
import com.example.myapplication.model.Common;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListCommonActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Common> commons;
    AdapterListCommon adaptercomomn;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_common);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://hasanlo.ir")
                .build();

        MyApi myApi = retrofit.create(MyApi.class);
        myApi.getCommon().enqueue(new Callback<List<Common>>() {
            @Override
            public void onResponse(Call<List<Common>> call, retrofit2.Response<List<Common>> response) {
                List<Common> commons1 = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adaptercomomn = new AdapterListCommon(getApplicationContext(), commons1);
                recyclerView.setAdapter(adaptercomomn);
            }
            @Override
            public void onFailure(Call<List<Common>> call, Throwable t) { }
        });
        recyclerView = findViewById(R.id.recyclerViewcommon);
        commons = new ArrayList<>();
    }
}