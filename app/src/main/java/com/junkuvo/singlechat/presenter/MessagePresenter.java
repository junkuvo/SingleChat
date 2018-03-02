package com.junkuvo.singlechat.presenter;

import com.junkuvo.singlechat.model.Message;
import com.junkuvo.singlechat.repository.MessageRepository;
import com.junkuvo.singlechat.repository.MessageRepositoryInterface;
import com.junkuvo.singlechat.view.MainActivity;

import io.realm.RealmResults;

public class MessagePresenter implements MessagePresenterInterface {

    private MainActivity view;
    private MessageRepositoryInterface messageRepository;
    private MessageRepositoryInterface.OnGetAllMessageCallback onGetAllMessageCallback;
    private MessageRepositoryInterface.OnAddMessageCallback onAddMessageCallback;

    public MessagePresenter(MainActivity view) {
        this.view = view;
        this.messageRepository = new MessageRepository();
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
            public void onSuccess(RealmResults<Message> messages) {
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
