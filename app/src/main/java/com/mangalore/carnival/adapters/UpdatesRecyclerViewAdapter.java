package com.mangalore.carnival.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mangalore.carnival.R;
import com.mangalore.carnival.model.UpdatesResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bapspatil
 */

public class UpdatesRecyclerViewAdapter extends RecyclerView.Adapter<UpdatesRecyclerViewAdapter.UpdatesViewHolder> {
    private ArrayList<UpdatesResponse> mUpdatesList;
    private Context mContext;

    public UpdatesRecyclerViewAdapter(Context context, ArrayList<UpdatesResponse> updatesList) {
        this.mContext = context;
        this.mUpdatesList = updatesList;
    }

    @Override
    public UpdatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_updates_item, parent, false);
        return new UpdatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UpdatesViewHolder holder, int position) {
        holder.mTitleTextView.setText(mUpdatesList.get(position).getTitle());
        holder.mBodyTextView.setText(mUpdatesList.get(position).getBody());
        holder.mTimestampTextView.setText(mUpdatesList.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        if(mUpdatesList == null) return 0;
        else return mUpdatesList.size();
    }

    public class UpdatesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_tv) TextView mTitleTextView;
        @BindView(R.id.body_tv) TextView mBodyTextView;
        @BindView(R.id.timestamp_tv) TextView mTimestampTextView;

        public UpdatesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
