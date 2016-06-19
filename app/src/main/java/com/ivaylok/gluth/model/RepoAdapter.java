package com.ivaylok.gluth.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivaylok.gluth.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smn on 6/17/16.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<RepoResponse> mRepoList;
    private RepoClickListener mListener;

    public RepoAdapter(RepoClickListener listener, LayoutInflater inflater) {
        mListener = listener;
        mInflater = inflater;
        mRepoList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        RepoResponse currRepo = mRepoList.get(position);

        holder.mName.setText(currRepo.getName());
        holder.mDescription.setText(currRepo.getDescription());
        holder.mLanguage.setText(currRepo.getLanguage());

    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public void addRepos(List<RepoResponse> repoResponses) {
        mRepoList.addAll(repoResponses);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mName, mDescription, mLanguage;

        public Holder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.textViewRepoName);
            mDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            mLanguage = (TextView) itemView.findViewById(R.id.textViewRepoLanguage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(), mRepoList.get(getAdapterPosition()).getName());
        }
    }

    public interface RepoClickListener {

        void onClick(int position, String name);
    }
}
