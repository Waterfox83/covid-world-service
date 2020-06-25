package com.jda.covidconsumer;

import com.jda.covidconsumer.kafka.consumer.Message;
import com.jda.covidconsumer.kafka.consumer.MyDateTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Date;

public class GsonTest {

  @Test
  public void testDeserialization() {
    String json = "{\"statisticDate\":1587126190000}";
    Gson gson  = new GsonBuilder().registerTypeAdapter(Date.class,new MyDateTypeAdapter()).create();
    Message obj = gson.fromJson(json, Message.class);
    System.out.println(obj);
  }
}
