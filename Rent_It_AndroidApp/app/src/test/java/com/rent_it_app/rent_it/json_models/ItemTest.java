package com.rent_it_app.rent_it.json_models;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yun Qi on 3/19/2017.
 */
public class ItemTest {
    @Test
    public void getId() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(item, "an_id");

        //when
        final String result = item.getId();

        //then
        assertEquals("field wasn't retrieved properly", result, "an_id");
    }

    @Test
    public void setId() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setId("an_id");

        //then
        final Field field = item.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "an_id");
    }

    @Test
    public void getUid() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("uid");
        field.setAccessible(true);
        field.set(item, "a_uid");

        //when
        final String result = item.getUid();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_uid");

    }

    @Test
    public void setUid() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setUid("a_uid");

        //then
        final Field field = item.getClass().getDeclaredField("uid");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_uid");

    }

    @Test
    public void getTitle() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(item, "a_title");

        //when
        final String result = item.getTitle();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_title");


    }

    @Test
    public void setTitle() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setTitle("a_title");

        //then
        final Field field = item.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_title");

    }

    @Test
    public void getDescription() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("description");
        field.setAccessible(true);
        field.set(item, "a_description");

        //when
        final String result = item.getDescription();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_description");

    }

    @Test
    public void setDescription() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setDescription("a_description");

        //then
        final Field field = item.getClass().getDeclaredField("description");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_description");

    }

    @Test
    public void getCondition() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("condition");
        field.setAccessible(true);
        field.set(item, "a_condition");

        //when
        final String result = item.getCondition();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_condition");

    }

    @Test
    public void setCondition() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setCondition("a_condition");

        //then
        final Field field = item.getClass().getDeclaredField("condition");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_condition");

    }




    @Test
    public void getCity() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("city");
        field.setAccessible(true);
        field.set(item, "a_city");

        //when
        final String result = item.getCity();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_city");

    }

    @Test
    public void setCity() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setCity("a_city");

        //then
        final Field field = item.getClass().getDeclaredField("city");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_city");

    }

    @Test
    public void getZipcode() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("zipcode");
        field.setAccessible(true);
        field.set(item, 0);

        //when
        final Integer result = item.getZipcode();

        //then
        assertSame("field wasn't retrieved properly", result, 0);

    }

    @Test
    public void setZipcode() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setZipcode(0);

        //then
        final Field field = item.getClass().getDeclaredField("zipcode");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), 0);

    }

    @Test
    public void getCategory() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("category");
        field.setAccessible(true);
        field.set(item, "a_category");

        //when
        final String result = item.getCategory();

        //then
        assertEquals("field wasn't retrieved properly", result, "a_category");
    }

    @Test
    public void setCategory() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setCategory("a_category");

        //then
        final Field field = item.getClass().getDeclaredField("category");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_category");

    }
/*
    @Test
    public void getTags() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("tags");
        field.setAccessible(true);
        field.set(item, "a_tags");

        //when
        final List result = item.getTags();

        //then
        assertSame("field wasn't retrieved properly", result, "a_tags");

    }

    @Test
    public void setTags() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setTags("t","t1");

        //then
        final Field field = item.getClass().getDeclaredField("tags");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_tags");

    }
    */

    @Test
    public void getValue() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("value");
        field.setAccessible(true);
        field.set(item, 0.0);

        //whenSame
        final double result = item.getValue();

        //then
        assertEquals( result, 0.0, 0);
    }


    @Test
    public void setValue() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setValue(0.0);

        //then
        final Field field = item.getClass().getDeclaredField("value");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), 0.0);

    }

    @Test
    public void getRate() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("rate");
        field.setAccessible(true);
        field.set(item, 0.0);

        //when
        final Double result = item.getRate();

        //then
        assertEquals( result, 0.0,0);

    }

    @Test
    public void setRate() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setRate(0.0);

        //then
        final Field field = item.getClass().getDeclaredField("rate");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), 0.0);

    }

    @Test
    public void getImage() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("image");
        field.setAccessible(true);
        field.set(item, "an_image");

        //when
        final String result = item.getImage();

        //then
        assertEquals("field wasn't retrieved properly", result, "an_image");

    }

    @Test
    public void setImage() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setImage("a_image");

        //then
        final Field field = item.getClass().getDeclaredField("image");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "a_image");

    }

    @Test
    public void getVisible() throws Exception {
        //given
        final Item item = new Item();
        final Field field = item.getClass().getDeclaredField("visible");
        field.setAccessible(true);
        field.set(item, false);

        //when
        final Boolean result = item.getVisible();

        //then
        assertSame("field wasn't retrieved properly", result, false);

    }

    @Test
    public void setVisible() throws Exception {
        //given
        final Item item = new Item();

        //when
        item.setVisible(false);

        //then
        final Field field = item.getClass().getDeclaredField("visible");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), false);

    }

}