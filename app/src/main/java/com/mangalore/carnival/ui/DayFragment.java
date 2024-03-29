package com.mangalore.carnival.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mangalore.carnival.R;
import com.mangalore.carnival.adapters.EventsRecyclerViewAdapter;
import com.mangalore.carnival.model.Event;
import com.mangalore.carnival.model.EventsResponse;
import com.mangalore.carnival.network.RetrofitAPI;
import com.mangalore.carnival.utils.NetworkUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.aviran.cookiebar2.CookieBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bapspatil
 */

public class DayFragment extends Fragment {
    @BindView(R.id.events_rv)
    RecyclerView eventsRecyclerView;
    @BindView(R.id.progress_bar)
    AVLoadingIndicatorView progressBar;

    private static final String DAY = "day";
    private ArrayList<Event> eventsList = new ArrayList<>();
    private EventsRecyclerViewAdapter eventsAdapter;

    public DayFragment() {
        // Required empty public constructor
    }

    public static DayFragment newInstance(int day) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        args.putInt(DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        ButterKnife.bind(this, view);

        recyclerViewInit();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            int dayNumber = getArguments().getInt(DAY);
            fetchDayEvents(dayNumber);
        }
    }

    private void recyclerViewInit() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        eventsRecyclerView.setLayoutManager(layoutManager);

        eventsAdapter = new EventsRecyclerViewAdapter(getContext(), eventsList);
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(eventsAdapter);
        eventsRecyclerView.setAdapter(alphaInAnimationAdapter);
    }

    public void fetchDayEvents(int dayNumber) {
        RetrofitAPI retrofitAPI = NetworkUtils.getCacheEnabledRetrofit(getContext()).create(RetrofitAPI.class);
        Call<EventsResponse> eventsResponseCall = retrofitAPI.getEvents();
        switch (dayNumber) {
            case 0:
                eventsResponseCall.enqueue(new Callback<EventsResponse>() {
                    @Override
                    public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                        if (response.body() != null) {
                            eventsList.addAll(response.body().getDayOneEvents());
                            eventsAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            eventsRecyclerView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<EventsResponse> call, Throwable t) {
                        CookieBar.build(getActivity())
                                .setTitle("Network problem, bruh!")
                                .setMessage("Make sure you're connected to the Internet, and launch the app again.")
                                .setDuration(7000)
                                .show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
                break;
            case 1:
                eventsResponseCall.enqueue(new Callback<EventsResponse>() {
                    @Override
                    public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                        if (response.body() != null) {
                            eventsList.addAll(response.body().getDayTwoEvents());
                            eventsAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            eventsRecyclerView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<EventsResponse> call, Throwable t) {
                        CookieBar.build(getActivity())
                                .setTitle("Network problem, bruh!")
                                .setMessage("Make sure you're connected to the Internet, and launch the app again.")
                                .setDuration(7000)
                                .show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
                break;
        }
    }
}
