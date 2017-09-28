package com.example.client;

import java.time.Instant;
import java.util.List;

public class ClientStory {

  static Builder builder() {
    return new Builder();
  }

  private final String id;
  private final String title;
  private final ClientAuthor author;
  private final String body;
  private final List<String> tags;
  private final Instant publishedAt;

  public String id() {
    return id;
  }

  public String title() {
    return title;
  }

  public ClientAuthor author() {
    return author;
  }

  public String body() {
    return body;
  }

  public List<String> tags() {
    return tags;
  }

  public Instant publishedAt() {
    return publishedAt;
  }

  private ClientStory(String id, String title, ClientAuthor author, String body, List<String> tags, Instant publishedAt) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.body = body;
    this.tags = tags;
    this.publishedAt = publishedAt;
  }

  static class Builder {

    private String id;
    private String title;
    private ClientAuthor author;
    private String body;
    private List<String> tags;
    private Instant publishedAt;

    private Builder() {
    }

    Builder id(String id) {
      this.id = id;
      return this;
    }

    Builder title(String title) {
      this.title = title;
      return this;
    }

    Builder author(ClientAuthor author) {
      this.author = author;
      return this;
    }

    Builder body(String body) {
      this.body = body;
      return this;
    }

    Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    Builder publishedAt(Instant publishedAt) {
      this.publishedAt = publishedAt;
      return this;
    }

    ClientStory build() {
      return new ClientStory(id, title, author, body, tags, publishedAt);
    }
  }
}
