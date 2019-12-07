package com.mangalore.carnival.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mangalore.carnival.R;
import com.mangalore.carnival.adapters.TeamRecyclerViewAdapter;
import com.mangalore.carnival.model.Team;
import com.mangalore.carnival.model.TeamResponse;
import com.mangalore.carnival.network.RetrofitAPI;
import com.mangalore.carnival.utils.NetworkUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.aviran.cookiebar2.CookieBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
**  Created by bapspatil
*/

public class TeamFragment extends Fragment {

    private ArrayList<Team> techTeam = new ArrayList<>();
    private ArrayList<Team> decoTeam = new ArrayList<>();
    private ArrayList<Team> logisticsTeam = new ArrayList<>();
    private TeamRecyclerViewAdapter techTeamAdapter, decoTeamAdapter, logisticsTeamAdapter;

    @BindView(R.id.tech_team_rv)
    RecyclerView techTeamRecyclerView;
    @BindView(R.id.camp_team_rv)
    RecyclerView decoTeamRecyclerView;
    @BindView(R.id.logistics_team_rv)
    RecyclerView logisticsTeamRecyclerView;
    @BindView(R.id.appbar)
    AppBarLayout appBar;
    @BindView(R.id.collapsing_bar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    AVLoadingIndicatorView progressBar;
    @BindView(R.id.nested_sv)
    NestedScrollView scrollView;

    public TeamFragment() {
        // Required empty public constructor
    }

    private void appBarInit() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(getContext(), android.R.color.white));
        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.titillium);
        collapsingToolbar.setCollapsedTitleTypeface(typeface);
        collapsingToolbar.setExpandedTitleTypeface(typeface);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        ButterKnife.bind(this, view);
        appBarInit();

        recyclerViewInit();

        fetchTeam();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void recyclerViewInit() {
        techTeamRecyclerView.setLayoutManager(lLM());
        decoTeamRecyclerView.setLayoutManager(lLM());
        logisticsTeamRecyclerView.setLayoutManager(lLM());

        techTeamAdapter = new TeamRecyclerViewAdapter(getContext(), techTeam);
        decoTeamAdapter = new TeamRecyclerViewAdapter(getContext(), decoTeam);
        logisticsTeamAdapter = new TeamRecyclerViewAdapter(getContext(), logisticsTeam);

        techTeamRecyclerView.setAdapter(animate(techTeamAdapter));
        decoTeamRecyclerView.setAdapter(animate(decoTeamAdapter));
        logisticsTeamRecyclerView.setAdapter(animate(logisticsTeamAdapter));
    }

    private void fetchTeam() {
        // Making it work offline
        RetrofitAPI retrofitAPI = NetworkUtils.getCacheEnabledRetrofit(getContext()).create(RetrofitAPI.class);
        Call<TeamResponse> teamResponseCall = retrofitAPI.getTeam();
        teamResponseCall.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if(response.body() != null) {
                    techTeam.addAll(response.body().getTechTeam());
                    techTeamAdapter.notifyDataSetChanged();

                    decoTeam.addAll(response.body().getCampTeam());
                    decoTeamAdapter.notifyDataSetChanged();

                    logisticsTeam.addAll(response.body().getLogisticsTeam());
                    logisticsTeamAdapter.notifyDataSetChanged();

                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                CookieBar.build(getActivity())
                        .setTitle("Network problem, bruh!")
                        .setMessage("Make sure you're connected to the Internet, and launch the app again.")
                        .setDuration(7000)
                        .show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private RecyclerView.LayoutManager lLM() {
        return new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
    }

    private ScaleInAnimationAdapter animate(TeamRecyclerViewAdapter teamRecyclerViewAdapter) {
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(teamRecyclerViewAdapter);
        scaleInAnimationAdapter.setDuration(200);
        return scaleInAnimationAdapter;
    }
}
