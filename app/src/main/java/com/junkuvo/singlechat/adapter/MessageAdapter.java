package com.junkuvo.singlechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junkuvo.singlechat.R;
import com.junkuvo.singlechat.model.Message;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class MessageAdapter extends RealmRecyclerViewAdapter {

    private RealmResults<Message> messages;

    public MessageAdapter(@NonNull Context context, @Nullable RealmResults<Message> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
        messages = data;
    }

    public MessageAdapter(@NonNull Context context, boolean autoUpdate) {
        super(context, null, autoUpdate);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (messages != null && messages.get(position) != null) {
            ((MessageViewHolder) holder).tvBody.setText(messages.get(position).getBody());
        }
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvBody;

        public MessageViewHolder(View itemView) {
            super(itemView);
            tvBody = itemView.findViewById(R.id.tv_body);
        }
    }
}
