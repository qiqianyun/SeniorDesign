package com.rent_it_app.rent_it.json_models;

import org.junit.Test;

import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Yun Qi on 3/19/2017.
 */

public class ChatTest {
    @Test
    public void getMsg() throws Exception{
        //given
        final Chat chat = new Chat();
        final Field field = chat.getClass().getDeclaredField("msg");
        field.setAccessible(true);
        field.set(chat, "a_msg");

        //when
        final String result = chat.getMsg();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_msg");
    }
    @Test
    public void setMsg() throws Exception{
        //given
        final Chat chat = new Chat();

        //when
        chat.setMsg("a_msg");

        //then
        final Field field = chat.getClass().getDeclaredField("msg");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(chat), "a_msg");
    }
    @Test
    public void getSender() throws Exception{
        //given
        final Chat chat = new Chat();
        final Field field = chat.getClass().getDeclaredField("sender");
        field.setAccessible(true);
        field.set(chat, "a_sender");

        //when
        final String result = chat.getSender();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_sender");
    }
    @Test
    public void setSender() throws Exception{
        //given
        final Chat chat = new Chat();

        //when
        chat.setSender("a_sender");

        //then
        final Field field = chat.getClass().getDeclaredField("sender");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(chat), "a_sender");
    }
    @Test

    public void getStatus() throws Exception {
        //given
        final Chat chat = new Chat();
        final Field field = chat.getClass().getDeclaredField("status");
        field.setAccessible(true);
        field.set(chat, 1);

        //when
        final int result = chat.getStatus();

        //then
        assertEquals("field wasn't retrieved properly", result, 1);
    }

    @Test
    public void setStatus() throws Exception {
        //given
        final Chat chat = new Chat();

        //when
        chat.setStatus(1);

        //then
        final Field field = chat.getClass().getDeclaredField("status");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(chat), 1);
    }

}
