package com.example.dependencies;

import com.example.client.Author;
import com.example.client.Client;
import com.google.common.collect.ImmutableList;

import java.time.Instant;
import java.util.List;

public class App {

  public static void main(final String[] args) {
    App app = new App(Client.getInstance(), new Terminal());

    app.addStories();
    app.printStories();
  }

  private final Client client;
  private final Terminal terminal;

  public App(Client client, Terminal terminal) {
    this.client = client;
    this.terminal = terminal;
  }

  private void addStories() {
    String firstStoryId = client.initializeNewStory("www.paper-monthly.com");
    client.addStoryInformation(firstStoryId,
        "Top Salesman",
        "Some people will tell you salesman is a bad word. They'll conjure up images of used car " +
            "dealers and door to door charlatans. This is our duty: to change their perception.",
        ImmutableList.of("salesman", "salesman of the year", "history"));
    client.addAuthorInformation(firstStoryId,
        "Jan",
        "Gould");
    client.publishStory(firstStoryId);

    String secondStoryId = client.initializeNewStory("www.paper-monthly.com");
    client.addStoryInformation(secondStoryId,
        "Dunder Mifflin Infinity",
        "It's like Facebook, but full of paper salesmen.",
        ImmutableList.of("salesman", "social network", "paper", "2.0"));
    client.addAuthorInformation(secondStoryId,
        "Creed",
        "Bratton");
    client.publishStory(secondStoryId);
  }

  private void printStories() {
    client.stories().forEach((siteName, stories) -> {
      terminal.printHeader(siteName);
      terminal.printSeparator();

      stories.forEach(story -> {
        String title = story.title();
        Author author = story.author();
        String body = story.body();
        List<String> tags = story.tags();
        Instant publishTime = story.publishedAt();

        terminal.printStory(title, author.firstName(), author.lastName(), body, tags, publishTime);
        terminal.printSeparator();
      });
    });
  }
}
