package com.junkuvo.singlechat.repository;

import com.junkuvo.singlechat.model.Message;

import io.realm.RealmResults;

/**
 * リポジトリ側に実装したい機能のメソッドと
 * リポジトリからの通知をPresenterに伝えるコールバックを持つinterface
 */
public interface MessageRepositoryInterface {
    void getAllMessages(OnGetAllMessageCallback onGetAllMessageCallback);

    interface OnGetAllMessageCallback {
        void onSuccess(RealmResults<Message> messages);

        void onError(String message);
    }

    void addMessage(Message message, OnAddMessageCallback onAddMessageCallback);

    interface OnAddMessageCallback {
        void onSuccess();

        void onError(String message);
    }

    void editMessageById(Message message, OnEditedCallback onEditedCallback);

    interface OnEditedCallback {
        void onSuccess();

        void onError(String message);
    }

    void deleteMessageById(long id, OnDeletedCallback onDeletedCallback);

    interface OnDeletedCallback {
        void onSuccess();

        void onError(String message);
    }

}
