package com.ivaylok.github.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivaylok.github.R;
import com.ivaylok.github.mvp.model.SingleRepoResponse;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.ArrayList;
import java.util.List;

public class SingleRepoAdapter extends RecyclerView.Adapter<SingleRepoAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<SingleRepoResponse> mSingleRepoList;
    private GithubClickListener mListener;

    public SingleRepoAdapter(GithubClickListener mListener, LayoutInflater mInflater) {
        this.mListener = mListener;
        this.mInflater = mInflater;
        mSingleRepoList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_single_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        SingleRepoResponse currRepo = mSingleRepoList.get(position);

        holder.mName.setText(currRepo.getName());
    }

    @Override
    public int getItemCount() {
        return mSingleRepoList.size();
    }

    public void addSingleRepos(List<SingleRepoResponse> singleRepoResponse) {
        mSingleRepoList.addAll(singleRepoResponse);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mName;

        public Holder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.textViewSingleRepoName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(), mSingleRepoList.get(getAdapterPosition()).getName());
        }
    }
}
