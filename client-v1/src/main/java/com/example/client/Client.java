package com.example.client;

import com.google.common.collect.ImmutableMap;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

public class Client {

  private static Client instance;

  public static Client getInstance() {
    if (instance == null) {
      instance = new Client();
    }

    return instance;
  }

  private Map<String, LinkedList<Map<String, Object>>> storiesBySite;

  private Client() {
    this.storiesBySite = new HashMap<>();
  }

  public void addStory(String websiteName, String title, String authorFirstName, String authorLastName, String authorLocation, String body, String[] tags, Instant publishTime) {
    storiesBySite.compute(websiteName, (siteName, stories) -> {
      if (stories == null) {
        stories = new LinkedList<>();
      }

      stories.addLast(ImmutableMap.of(
          "title", title,
          "author", ImmutableMap.of(
              "firstName", authorFirstName,
              "lastName", authorLastName,
              "location", authorLocation),
          "body", body,
          "tags", tags,
          "publishTime", publishTime));

      return stories;
    });
  }

  public Map<String, LinkedList<Map<String, Object>>> getStories() {
    return storiesBySite;
  }

  public Map<String, Object> getLatestStory(String websiteName) {
    return Optional.ofNullable(storiesBySite.get(websiteName))
        .map(LinkedList::peekFirst)
        .orElse(null);
  }
}
