package com.junkuvo.singlechat.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junkuvo.singlechat.R;
import com.junkuvo.singlechat.entity.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    private List<Message> messages;

    public MessageAdapter(@Nullable List<Message> data) {
        messages = data;
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

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvBody;

        public MessageViewHolder(View itemView) {
            super(itemView);
            tvBody = itemView.findViewById(R.id.tv_body);
        }
    }
}
