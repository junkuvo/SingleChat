package com.junkuvo.singlechat.presenter;

import com.junkuvo.singlechat.entity.Message;

import java.util.List;

public interface MessagePresenterInterface {
    interface Presenter {

        void getAllMessage();

        void addMessage(Message message);

        void subscribeCallbacks();

        void unSubscribeCallbacks();
    }

    interface View {
        void showMessages(List<Message> messages);

        void showAddedMessage();
    }
}
