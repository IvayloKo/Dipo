package com.ivaylok.github.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivaylok.github.R;
import com.ivaylok.github.mvp.model.FollowersResponse;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<FollowersResponse> mFollowersList;
    private GithubClickListener mListener;

    public FollowersAdapter(GithubClickListener listener, LayoutInflater inflater) {
        mListener = listener;
        mInflater = inflater;
        mFollowersList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_followers, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        FollowersResponse currFollower = mFollowersList.get(position);

        holder.mLogin.setText(currFollower.getLogin());
        // TODO need to add the Image
    }

    @Override
    public int getItemCount() {
        return mFollowersList.size();
    }

    public void addFollowers(List<FollowersResponse> followersResponses) {
        mFollowersList.addAll(followersResponses);
        notifyDataSetChanged();
    }



    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mLogin;
        private ImageView mAvatar;

        public Holder(View itemView) {
            super(itemView);
            mLogin = (TextView) itemView.findViewById(R.id.textViewFollowerName);
            mAvatar = (ImageView) itemView.findViewById(R.id.imageViewAvatar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(), mFollowersList.get(getAdapterPosition()).getLogin());
        }
    }



}

