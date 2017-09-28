package com.example.dependencies;

import com.example.client.ClientAuthor;
import com.example.client.Client;
import com.google.common.collect.ImmutableList;

import java.time.Instant;
import java.util.List;

public class App {

  public static void main(final String[] args) {
    App app = new App(Client.getInstance(), new Terminal());

    app.addStories("www.paper-monthly.com", ImmutableList.of(
        Story.builder()
            .withTitle("Top Salesman")
            .withBody("Some people will tell you salesman is a bad word. They'll conjure up images of used car " +
                "dealers and door to door charlatans. This is our duty: to change their perception.")
            .withAuthor(new Author("Jan", "Gould"))
            .withTags("salesman", "salesman of the year", "history")
            .build(),
        Story.builder()
            .withTitle("Dunder Mifflin Infinity")
            .withBody("It's like Facebook, but full of paper salesmen")
            .withAuthor(new Author("Creed", "Bratton"))
            .withTags("salesman", "social network", "paper", "2.0")
            .build()));
    app.printStories();
  }

  private final Client client;
  private final Terminal terminal;

  public App(Client client, Terminal terminal) {
    this.client = client;
    this.terminal = terminal;
  }

  public void addStories(String siteName, List<Story> stories) {
    stories.forEach(story -> {
      String storyId = client.initializeNewStory(siteName);
      client.addStoryInformation(storyId,
          story.title(),
          story.body(),
          story.tags());
      client.addAuthorInformation(storyId,
          story.author().firstName(),
          story.author().lastName());
      client.publishStory(storyId);
    });
  }

  public void printStories() {
    client.stories().forEach((siteName, stories) -> {
      terminal.printHeader(siteName);
      terminal.printSeparator();

      stories.forEach(story -> {
        String title = story.title();
        ClientAuthor author = story.author();
        String body = story.body();
        List<String> tags = story.tags();
        Instant publishTime = story.publishedAt();

        terminal.printStory(title, author.firstName(), author.lastName(), body, tags, publishTime);
        terminal.printSeparator();
      });
    });
  }
}
