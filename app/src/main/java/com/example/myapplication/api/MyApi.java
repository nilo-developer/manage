package com.example.myapplication.api;

import com.example.myapplication.model.Common;
import com.example.myapplication.model.ListSeminar;
import com.example.myapplication.model.Seminar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    @GET("https://hasanlo.ir/android/nilofar/recyclerwithvolley/data.json")
    Call<List<Seminar>> getSeminars();

    @GET("https://hasanlo.ir/android/nilofar/recyclerwithvolley/data.json")
    Call<List<ListSeminar>> getSeminars1();

    @GET("https://hasanlo.ir/android/nilofar/recyclerwithvolley/common.json")
    Call<List<Common>> getCommon();

}
