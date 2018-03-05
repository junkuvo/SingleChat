package com.junkuvo.singlechat.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.junkuvo.singlechat.R;
import com.junkuvo.singlechat.adapter.MessageAdapter;
import com.junkuvo.singlechat.entity.Message;
import com.junkuvo.singlechat.presenter.MessagePresenter;
import com.junkuvo.singlechat.presenter.MessagePresenterInterface;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MessagePresenterInterface.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bt_send)
    AppCompatButton btSend;
    @BindView(R.id.et_message)
    AppCompatEditText etMessage;

    private MessageAdapter messageAdapter;
    /**
     * このPresenterが画像とか動画とかも吸収できるようにしたい
     */
    private MessagePresenterInterface.Presenter messagePresenter;

    private Message message = new Message();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btSend.setOnClickListener(onSendClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        messagePresenter = new MessagePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        messagePresenter.subscribeCallbacks();
        messagePresenter.getAllMessage();
    }

    @Override
    protected void onPause() {
        super.onPause();
        messagePresenter.unSubscribeCallbacks();
    }

    @Override
    public void showMessages(List<Message> messages) {
        if (messageAdapter == null) {
            messageAdapter = new MessageAdapter(messages);
            recyclerView.setAdapter(messageAdapter);
        } else {
            // add messages of next page
        }
        recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1);
    }

    @Override
    public void showAddedMessage() {
        recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1);
        etMessage.setText("");
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    View.OnClickListener onSendClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            message.setId(System.currentTimeMillis());
            message.setBody(etMessage.getText().toString());
            message.setCreatedAt(new Date());
            messagePresenter.addMessage(message);
        }
    };
}
