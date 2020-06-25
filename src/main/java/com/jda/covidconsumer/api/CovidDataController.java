package com.jda.covidconsumer.api;

import com.jda.covidconsumer.api.stats.WorldStatsMessage;
import com.jda.covidconsumer.service.CovidWorldService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(Routes.LATEST_VERSION)
public class CovidDataController {
  private final CovidWorldService covidWorldService;

  @Autowired
  public CovidDataController(CovidWorldService covidWorldService) {
    this.covidWorldService = covidWorldService;
  }

  @GetMapping(Routes.WORLD_STAT)
  public WorldStatsMessage getWorldStats() throws IOException {
    log.info("Calling covidWorldService.getWorldStats().");
    return covidWorldService.getWorldStats();
  }
}
