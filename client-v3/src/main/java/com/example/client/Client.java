package com.example.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Client {

  private static Client instance;

  public static Client getInstance() {
    if (instance == null) {
      instance = new Client();
    }

    return instance;
  }

  private Map<String, LinkedList<Story>> storiesBySite;

  private Client() {
    this.storiesBySite = new HashMap<>();
  }

  public void addStory(String websiteName, Function<Story.Builder, Story> storyBuilder) {
    storiesBySite.compute(websiteName, (siteName, stories) -> {
      if (stories == null) {
        stories = new LinkedList<>();
      }

      stories.addLast(storyBuilder.apply(Story.builder()));

      return stories;
    });
  }

  public Map<String, LinkedList<Story>> getStories() {
    return storiesBySite;
  }

  public Story getLatestStory(String websiteName) {
    return Optional.ofNullable(storiesBySite.get(websiteName))
        .map(LinkedList::peekFirst)
        .orElse(null);
  }
}
