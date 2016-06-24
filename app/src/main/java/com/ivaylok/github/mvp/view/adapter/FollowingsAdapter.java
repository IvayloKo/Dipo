package com.ivaylok.github.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ivaylok.github.R;
import com.ivaylok.github.mvp.model.FollowingsResponse;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.ArrayList;
import java.util.List;

public class FollowingsAdapter extends RecyclerView.Adapter<FollowingsAdapter.Holder>  {

    private final LayoutInflater mInflater;
    private List<FollowingsResponse> mFollowingsList;
    private GithubClickListener mListener;

    public FollowingsAdapter(GithubClickListener mListener, LayoutInflater mInflater) {
        this.mListener = mListener;
        this.mInflater = mInflater;
        mFollowingsList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_followers, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        FollowingsResponse currFollowing = mFollowingsList.get(position);

        holder.mLogin.setText(currFollowing.getLogin());
        Glide.with(holder.itemView.getContext()).load(currFollowing.getAvatar_url()).into(holder.mAvatar);

    }

    @Override
    public int getItemCount() { return mFollowingsList.size(); }

    public void addFollowings(List<FollowingsResponse> followingsResponses) {
        mFollowingsList.addAll(followingsResponses);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            mListener.onClick(getLayoutPosition(), mFollowingsList.get(getAdapterPosition()).getLogin());
        }
    }
}
