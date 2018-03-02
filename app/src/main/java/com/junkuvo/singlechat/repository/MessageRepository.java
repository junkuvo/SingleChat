package com.junkuvo.singlechat.repository;

import android.support.annotation.NonNull;

import com.junkuvo.singlechat.entity.Message;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MessageRepository implements MessageRepositoryInterface {

    @Override
    public void getAllMessages(OnGetAllMessageCallback onGetAllMessageCallback) {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<Message> results = realm.where(Message.class).findAll().sort("id", Sort.ASCENDING);
            if (onGetAllMessageCallback != null) {
                onGetAllMessageCallback.onSuccess(results);
            }
        } catch (Exception e) {
            if (onGetAllMessageCallback != null) {
                onGetAllMessageCallback.onError(e.getMessage());
            }
        } finally {
            // getしたらcloseする
            realm.close();
        }
    }

    public void addMessage(final Message message, MessageRepositoryInterface.OnAddMessageCallback onAddMessageCallback) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    realm.insert(message);
                }
            });
            if (onAddMessageCallback != null) {
                onAddMessageCallback.onSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (onAddMessageCallback != null) {
                onAddMessageCallback.onError(e.getMessage());
            }
        } finally {
            realm.close();
        }
    }

    @Override
    public void editMessageById(Message message, OnEditedCallback onEditedCallback) {

    }

    @Override
    public void deleteMessageById(long id, OnDeletedCallback onDeletedCallback) {

    }
}
