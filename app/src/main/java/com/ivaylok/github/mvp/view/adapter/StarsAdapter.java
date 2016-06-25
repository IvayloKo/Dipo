package com.ivaylok.github.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivaylok.github.R;
import com.ivaylok.github.mvp.model.StarsResponse;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.ArrayList;
import java.util.List;

public class StarsAdapter extends RecyclerView.Adapter<StarsAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<StarsResponse> mStarsList;
    private GithubClickListener mListener;

    public StarsAdapter(GithubClickListener mListener, LayoutInflater mInflater) {
        this.mListener = mListener;
        this.mInflater = mInflater;
        mStarsList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_star, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        StarsResponse currStar = mStarsList.get(position);

        holder.mFullName.setText(currStar.getFull_name());
        holder.mStarsCount.setText(currStar.getStargazers_count());
    }

    @Override
    public int getItemCount() {
        return mStarsList.size();
    }

    public void addStars(List<StarsResponse> starsResponses) {
        mStarsList.addAll(starsResponses);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mFullName, mStarsCount;

        public Holder(View itemView) {
            super(itemView);
            mFullName = (TextView) itemView.findViewById(R.id.textViewStarName);
            mStarsCount = (TextView) itemView.findViewById(R.id.textViewStarCount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(), mStarsList.get(getAdapterPosition()).getName());
        }
    }
}
