package com.example.client;

import java.time.Instant;
import java.util.List;

public class Story {

  public static Builder builder() {
    return new Builder();
  }

  private final String id;
  private final String title;
  private final Author author;
  private final String body;
  private final List<String> tags;
  private final Instant publishedAt;

  public String id() {
    return id;
  }

  public String title() {
    return title;
  }

  public Author author() {
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

  private Story(String id, String title, Author author, String body, List<String> tags, Instant publishedAt) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.body = body;
    this.tags = tags;
    this.publishedAt = publishedAt;
  }

  public static class Builder {

    private String id;
    private String title;
    private Author author;
    private String body;
    private List<String> tags;
    private Instant publishedAt;

    private Builder() {
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder author(Author author) {
      this.author = author;
      return this;
    }

    public Builder body(String body) {
      this.body = body;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder publishedAt(Instant publishedAt) {
      this.publishedAt = publishedAt;
      return this;
    }

    public Story build() {
      return new Story(id, title, author, body, tags, publishedAt);
    }
  }
}
