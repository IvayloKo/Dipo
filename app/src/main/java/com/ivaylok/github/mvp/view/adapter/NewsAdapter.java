package com.ivaylok.github.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivaylok.github.R;
import com.ivaylok.github.mvp.model.NewsResponse;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<NewsResponse> mNewsList;
    private GithubClickListener mListener;

    public NewsAdapter(GithubClickListener mListener, LayoutInflater mInflater) {
        this.mListener = mListener;
        this.mInflater = mInflater;
        mNewsList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        NewsResponse currNews = mNewsList.get(position);

        holder.mEventType.setText(currNews.getType());
        holder.mRepoName.setText(currNews.getRepo());
        holder.mActorName.setText(currNews.getActor());
        holder.mPayloadAction.setText(currNews.getPayload());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void addNews(List<NewsResponse> newsResponse) {
        mNewsList.addAll(newsResponse);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mEventType, mActorName, mRepoName, mPayloadAction;

        public Holder(View itemView) {
            super(itemView);

            mEventType = (TextView) itemView.findViewById(R.id.textViewEventType);
            mActorName = (TextView) itemView.findViewById(R.id.textViewActorLogin);
            mRepoName = (TextView) itemView.findViewById(R.id.textViewNewsRepoName);
            mPayloadAction = (TextView) itemView.findViewById(R.id.textViewPayloadAction);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(), mNewsList.get(getAdapterPosition()).getType());
        }
    }
}
