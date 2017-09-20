package com.example.dependencies;

import com.example.client.Client;

import java.time.Instant;
import java.util.Arrays;
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
    Client.getInstance()
        .addStory(
            "www.paper-monthly.com",
            "Top Salesman",
            "Jan",
            "Gould",
            "Nashua, NH",
            "Some people will tell you salesman is a bad word. They'll conjure up images of used car " +
                "dealers and door to door charlatans. This is our duty: to change their perception.",
            new String[] { "salesman", "salesman of the year", "history" },
            Instant.now());

    Client.getInstance()
        .addStory(
            "www.paper-monthly.com",
            "Dunder Mifflin Infinity",
            "Creed",
            "Bratton",
            "Scranton, PA",
            "It's like Facebook, but full of paper salesmen.",
            new String[] { "salesman", "social network", "paper", "2.0" },
            Instant.now());
  }

  private static void printStories() {
    Client.getInstance().getStories().forEach((siteName, stories) -> {
      printHeader(siteName);
      printSeparator();

      stories.forEach(story -> {
        String title = (String) story.get("title");
        Map<String, String> author = (Map<String, String>) story.get("author");
        String body = (String) story.get("body");
        String[] tags = (String[]) story.get("tags");
        Instant publishTime = (Instant) story.get("publishTime");

        printStory(title, author.get("firstName"), author.get("lastName"), body, tags, publishTime);
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

  private static void printStory(String title, String authorFirstName, String authorLastName, String body, String[] tags, Instant publishTime) {
    System.out.format(HEADER_FORMAT, title, authorFirstName + " " + authorLastName, publishTime);
    System.out.format(BODY_FORMAT, body.substring(0, Math.min(body.length(), 75)) + "....");
    System.out.format(BODY_FORMAT, "Tags: " + Arrays.stream(tags).collect(Collectors.joining(", ")));
  }
}
