package com.example.client;

import com.google.common.collect.ImmutableMap;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Client {

  private static Client instance;

  public static Client getInstance() {
    if (instance == null) {
      instance = new Client();
    }

    return instance;
  }

  private Map<String, Map<String, Map<String, Object>>> storiesBySite;
  private Map<String, Set<String>> siteStoryIds;

  private Client() {
    this.storiesBySite = new HashMap<>();
    this.siteStoryIds = new HashMap<>();
  }

  public String initializeNewStory(String websiteName) {
    String newStoryId = UUID.randomUUID().toString();

    siteStoryIds.compute(websiteName, (siteName, storyIds) -> {
      storyIds = Optional.ofNullable(storyIds)
          .orElse(new HashSet<>());

      storyIds.add(newStoryId);

      return storyIds;
    });

    try {
      Thread.sleep(Duration.ofSeconds(5).toMillis());
    } catch (InterruptedException ignored) { }

    return newStoryId;
  }

  public void addAuthorInformation(String storyId, String authorFirstName, String authorLastName) {
    updateStory(storyId, story -> story.put(
        "author", ImmutableMap.of(
            "firstName", authorFirstName,
            "lastName", authorLastName)));
  }

  public void addStoryInformation(String storyId, String title, String body, List<String> tags) {
    updateStory(storyId, story -> story.putAll(
        ImmutableMap.of(
            "title", title,
            "body", body,
            "tags", tags)));
  }

  public void publishStory(String storyId) {
    updateStory(storyId, story -> story.put(
        "publishedAt", Instant.now()));
  }

  public Map<String, List<ClientStory>> stories() {
    return storiesBySite.entrySet().stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> entry.getValue().entrySet().stream()
                .map(e -> ClientStory.builder()
                    .id(e.getKey())
                    .title(getStoryAttribute(e, "title"))
                    .author(ClientAuthor.builder()
                        .firstName(getAuthorAttribute(e, "firstName"))
                        .lastName(getAuthorAttribute(e, "lastName"))
                        .build())
                    .body(getStoryAttribute(e, "body"))
                    .tags(getStoryAttribute(e, "tags"))
                    .publishedAt(getStoryAttribute(e, "publishedAt"))
                    .build())
                .collect(Collectors.toList())));
  }

  @SuppressWarnings("unchecked")
  private static <T> T getStoryAttribute(Map.Entry<String, Map<String, Object>> storyEntry, String attributeName) {
    return (T) storyEntry.getValue().get(attributeName);
  }

  @SuppressWarnings("unchecked")
  private static <T> T getAuthorAttribute(Map.Entry<String, Map<String, Object>> storyEntry, String attributeName) {
    return (T) ((Map<String, Object>) storyEntry.getValue().get("author")).get(attributeName);
  }

  private void updateStory(String storyId, Consumer<Map<String, Object>> storyUpdater) {
    storiesBySite.compute(websiteNameByStoryId(storyId), (siteName, stories) -> {
      stories = Optional.ofNullable(stories)
          .orElse(new HashMap<>());

      stories.compute(storyId, (sid, story) -> {
        story = Optional.ofNullable(story)
            .orElse(new HashMap<>());

        storyUpdater.accept(story);

        return story;
      });

      return stories;
    });
  }

  private String websiteNameByStoryId(String storyId) {
    return siteStoryIds.entrySet().stream()
        .filter(entry -> entry.getValue().contains(storyId))
        .map(Map.Entry::getKey)
        .findFirst().orElseThrow(() -> new RuntimeException("Story not initialized for: " + storyId));
  }
}
