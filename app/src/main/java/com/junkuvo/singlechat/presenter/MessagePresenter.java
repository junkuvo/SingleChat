package com.junkuvo.singlechat.presenter;

import android.support.annotation.VisibleForTesting;

import com.junkuvo.singlechat.entity.Message;
import com.junkuvo.singlechat.repository.MessageRepository;
import com.junkuvo.singlechat.repository.MessageRepositoryInterface;
import com.junkuvo.singlechat.view.MainActivity;

import java.util.List;

public class MessagePresenter implements MessagePresenterInterface.Presenter {

    private MainActivity view;
    private MessageRepositoryInterface messageRepository;
    @VisibleForTesting
    MessageRepositoryInterface.OnGetAllMessageCallback onGetAllMessageCallback;
    @VisibleForTesting
    MessageRepositoryInterface.OnAddMessageCallback onAddMessageCallback;

    public MessagePresenter(MainActivity view) {
        this.view = view;
        this.messageRepository = new MessageRepository();
    }

    @VisibleForTesting
    void setMessageRepository(MessageRepositoryInterface messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageRepositoryInterface getMessageRepository() {
        return messageRepository;
    }

    @Override
    public void getAllMessage() {
        messageRepository.getAllMessages(onGetAllMessageCallback);
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.addMessage(message, onAddMessageCallback);
    }

    @Override
    public void subscribeCallbacks() {
        onGetAllMessageCallback = new MessageRepositoryInterface.OnGetAllMessageCallback() {
            @Override
            public void onSuccess(List<Message> messages) {
                if (!view.isFinishing()) {
                    view.showMessages(messages);
                }
            }

            @Override
            public void onError(String message) {

            }
        };

        onAddMessageCallback = new MessageRepositoryInterface.OnAddMessageCallback() {
            @Override
            public void onSuccess() {
                if (!view.isFinishing()) {
                    view.showAddedMessage();
                }
            }

            @Override
            public void onError(String message) {

            }
        };
    }

    @Override
    public void unSubscribeCallbacks() {
        onGetAllMessageCallback = null;
        onAddMessageCallback = null;
    }
}
