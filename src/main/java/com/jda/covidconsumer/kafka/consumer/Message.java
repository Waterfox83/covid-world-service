package com.jda.covidconsumer.kafka.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
  long totalCases;
  long totalDeaths;
  long totalRecovered;
  int newCases;
  int newDeaths;
  Date statisticDate;

  public Message(int newCases) {
    this.newCases = newCases;
  }

  public static final class MessageBuilder {
    long totalCases;
    long totalDeaths;
    long totalRecovered;
    int newCases;
    int newDeaths;
    Date statisticDate;

    private MessageBuilder() {
    }

    public static MessageBuilder aMessage() {
      return new MessageBuilder();
    }

    public MessageBuilder withTotalCases(long totalCases) {
      this.totalCases = totalCases;
      return this;
    }

    public MessageBuilder withTotalDeaths(long totalDeaths) {
      this.totalDeaths = totalDeaths;
      return this;
    }

    public MessageBuilder withTotalRecovered(long totalRecovered) {
      this.totalRecovered = totalRecovered;
      return this;
    }

    public MessageBuilder withNewCases(int newCases) {
      this.newCases = newCases;
      return this;
    }

    public MessageBuilder withNewDeaths(int newDeaths) {
      this.newDeaths = newDeaths;
      return this;
    }

    public MessageBuilder withStatisticDate(Date statisticDate) {
      this.statisticDate = statisticDate;
      return this;
    }

    public Message build() {
      Message message = new Message();
      message.setTotalCases(totalCases);
      message.setTotalDeaths(totalDeaths);
      message.setTotalRecovered(totalRecovered);
      message.setNewCases(newCases);
      message.setNewDeaths(newDeaths);
      message.setStatisticDate(statisticDate);
      return message;
    }
  }
}
