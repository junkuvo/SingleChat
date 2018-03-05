package com.junkuvo.singlechat.presenter;

import com.google.common.collect.Lists;
import com.junkuvo.singlechat.entity.Message;
import com.junkuvo.singlechat.repository.MessageRepository;
import com.junkuvo.singlechat.repository.MessageRepositoryInterface;
import com.junkuvo.singlechat.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessagePresenterTest {
    private MessagePresenter messagePresenter;
    private MainActivity mainActivity;
    private MessageRepositoryInterface messageRepository;
    private Message message;
    private static List<Message> MESSAGES;


    private final long ID = 1L;
    private final String BODY = "test";
    private final Date CREATE_AT = new Date();

    @Before
    public void setUp() throws Exception {
        mainActivity = mock(MainActivity.class);
        messageRepository = mock(MessageRepository.class);
        messagePresenter = new MessagePresenter(mainActivity);
        messagePresenter.subscribeCallbacks();
        // TODO repositoryをDIしたらいいのでは？？
        messagePresenter.setMessageRepository(messageRepository);
        messageRepository = messagePresenter.getMessageRepository();
        message = new Message();
        message.setId(ID);
        message.setBody(BODY);
        message.setCreatedAt(CREATE_AT);

        MESSAGES = Lists.newArrayList(message, message, message);


    }

    @After
    public void tearDown() throws Exception {
        messagePresenter.unSubscribeCallbacks();
    }

    @Test
    public void getAllMessage() throws Exception {
        messagePresenter.getAllMessage();
        verify(messageRepository).getAllMessages(messagePresenter.onGetAllMessageCallback);
        messagePresenter.onGetAllMessageCallback.onSuccess(MESSAGES);
        verify(mainActivity).showMessages(MESSAGES);
    }

    @Test
    public void addMessage() throws Exception {
        messagePresenter.addMessage(message);
        verify(messageRepository).addMessage(message, messagePresenter.onAddMessageCallback);
        messagePresenter.onAddMessageCallback.onSuccess();
        verify(mainActivity).showAddedMessage();
    }
}