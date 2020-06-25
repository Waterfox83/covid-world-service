package com.jda.covidconsumer.service;

import com.jda.covidconsumer.api.stats.WorldStatsData;
import com.jda.covidconsumer.api.stats.WorldStatsMessage;
import com.jda.covidconsumer.api.stats.WorldStatsTranslator;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Slf4j
@Service
public class CovidWorldService {
  private final String HEADER_RAPIDAPI_HOST = "x-rapidapi-host";
  private final String HEADER_RAPIDAPI_KEY = "x-rapidapi-key";

  @Value("${coronavirus.worldStatisticsApi}")
  private String coronavirusWorldStatisticsApiUrl;

  @Value("${coronavirus.rapidApiHost}")
  private String coronavirusRapidApiHost;

  @Value("${rapidApiKey}")
  private String rapidApiKey;

  public WorldStatsMessage getWorldStats() throws IOException {
    log.info("Invoking RapidAPI to get data.");
    CloseableHttpClient httpclient = HttpClients.createDefault();

    HttpGet httpGet = new HttpGet(coronavirusWorldStatisticsApiUrl);
    httpGet.addHeader(HEADER_RAPIDAPI_HOST, coronavirusRapidApiHost);
    httpGet.addHeader(HEADER_RAPIDAPI_KEY, rapidApiKey);

    CloseableHttpResponse response = httpclient.execute(httpGet);
    WorldStatsMessage worldStatsMessage = null;
    WorldStatsData worldStatsData = null;
    try {
      HttpEntity entity = response.getEntity();
      String responseValue = EntityUtils.toString(entity);
      worldStatsData = (new Gson()).fromJson(responseValue, WorldStatsData.class);

      log.info("Response from RapidAPI: " + worldStatsData.toString());
      worldStatsMessage = WorldStatsTranslator.toKafkaMessageFormat(worldStatsData);
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      response.close();
    }
    return worldStatsMessage;
  }
}
