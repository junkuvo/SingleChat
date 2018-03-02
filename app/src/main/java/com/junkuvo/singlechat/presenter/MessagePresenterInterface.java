package com.junkuvo.singlechat.presenter;

import com.junkuvo.singlechat.entity.Message;

import io.realm.RealmResults;

public interface MessagePresenterInterface {
    void getAllMessage();

    void addMessage(Message message);

    void subscribeCallbacks();

    void unSubscribeCallbacks();

    interface View {
        void showMessages(RealmResults<Message> messages);

        void showAddedMessage();
    }
}
