package com.example.dependencies;

import com.example.client.Author;
import com.example.client.Client;
import com.google.common.collect.ImmutableList;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

  private static final String HEADER_FORMAT = "| %1$-30s | %2$-20s | %3$-24s |\n";
  private static final String BODY_FORMAT = "| %1$-80s |\n";
  private static final String SITE_NAME_HEADER_FORMAT = "| SITE: %1$-74s |\n";

  public static void main(final String[] args) {
    addStories();
    printStories();
  }

  private static void addStories() {
    Client client = Client.getInstance();

    String firstStoryId = client.initializeNewStory("www.paper-monthly.com");
    client.addStoryInformation(firstStoryId,
        "Top Salesman",
        "Some people will tell you salesman is a bad word. They'll conjure up images of used car " +
            "dealers and door to door charlatans. This is our duty: to change their perception.",
        ImmutableList.of("salesman", "salesman of the year", "history"));
    client.addAuthorInformation(firstStoryId,
        "Jan",
        "Gould",
        "Nashua, NH");
    client.publishStory(firstStoryId);

    String secondStoryId = client.initializeNewStory("www.paper-monthly.com");
    client.addStoryInformation(secondStoryId,
        "Dunder Mifflin Infinity",
        "It's like Facebook, but full of paper salesmen.",
        ImmutableList.of("salesman", "social network", "paper", "2.0"));
    client.addAuthorInformation(secondStoryId,
        "Creed",
        "Bratton",
        "Scranton, PA");
    client.publishStory(secondStoryId);
  }

  private static void printStories() {
    Client.getInstance().stories().forEach((siteName, stories) -> {
      printHeader(siteName);
      printSeparator();

      stories.forEach(story -> {
        String title = story.title();
        Author author = story.author();
        String body = story.body();
        List<String> tags = story.tags();
        Instant publishTime = story.publishedAt();

        printStory(title, author.firstName(), author.lastName(), body, tags, publishTime);
        printSeparator();
      });
    });
  }

  private static void printHeader(String siteName) {
    System.out.format(SITE_NAME_HEADER_FORMAT, siteName);
    System.out.format(HEADER_FORMAT, "TITLE", "AUTHOR", "PUBLISH DATE");
  }

  private static void printSeparator() {
    System.out.format(BODY_FORMAT, "--------------------------------------------------------------------------------");
  }

  private static void printStory(String title, String authorFirstName, String authorLastName, String body, List<String> tags, Instant publishTime) {
    System.out.format(HEADER_FORMAT, title, authorFirstName + " " + authorLastName, publishTime);
    System.out.format(BODY_FORMAT, body.substring(0, Math.min(body.length(), 75)) + "....");
    System.out.format(BODY_FORMAT, "Tags: " + tags.stream().collect(Collectors.joining(", ")));
  }
}
