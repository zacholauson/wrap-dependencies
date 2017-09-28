package com.example.dependencies;

import com.google.common.collect.Lists;

import java.util.List;

public class Story {

  static Builder builder() {
    return new Builder();
  }

  private final String title;
  private final Author author;
  private final String body;
  private final List<String> tags;

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

  private Story(String title, Author author, String body, List<String> tags) {
    this.title = title;
    this.author = author;
    this.body = body;
    this.tags = tags;
  }

  static class Builder {

    private String title;
    private Author author;
    private String body;
    private List<String> tags;

    private Builder() {
    }

    Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    Builder withAuthor(Author author) {
      this.author = author;
      return this;
    }

    Builder withBody(String body) {
      this.body = body;
      return this;
    }

    Builder withTags(String... tags) {
      this.tags = Lists.newArrayList(tags);
      return this;
    }

    Story build() {
      return new Story(title, author, body, tags);
    }
  }
}
