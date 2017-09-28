package com.example.dependencies;

import com.google.common.collect.Lists;

import java.time.Instant;
import java.util.List;

public class Story {

  public static Builder builder() {
    return new Builder();
  }

  private final String title;
  private final Author author;
  private final String body;
  private final List<String> tags;
  private final Instant publishedAt;

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

  private Story(String title, Author author, String body, List<String> tags, Instant publishedAt) {
    this.title = title;
    this.author = author;
    this.body = body;
    this.tags = tags;
    this.publishedAt = publishedAt;
  }

  public static class Builder {

    private String title;
    private Author author;
    private String body;
    private List<String> tags;
    private Instant publishTime;

    private Builder() {
    }

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder withAuthor(Author author) {
      this.author = author;
      return this;
    }

    public Builder withBody(String body) {
      this.body = body;
      return this;
    }

    public Builder withTags(String... tags) {
      this.tags = Lists.newArrayList(tags);
      return this;
    }

    public Builder withPublishTime(Instant publishTime) {
      this.publishTime = publishTime;
      return this;
    }

    Story build() {
      return new Story(title, author, body, tags, publishTime);
    }
  }
}
