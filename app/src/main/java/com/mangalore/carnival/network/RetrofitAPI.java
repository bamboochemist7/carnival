package com.mangalore.carnival.network;

import com.mangalore.carnival.model.EventsResponse;
import com.mangalore.carnival.model.TeamResponse;
import com.mangalore.carnival.model.UpdatesResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by bapspatil
 */

public interface RetrofitAPI {

    String BASE_URL = "https://raw.githubusercontent.com/bapspatil/Pantheon/master/";

    @GET("resources/team.json")
    Call<TeamResponse> getTeam();

    @GET("resources/events.json")
    Call<EventsResponse> getEvents();

    @GET("resources/updates.json")
    Call<ArrayList<UpdatesResponse>> getUpdates();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
