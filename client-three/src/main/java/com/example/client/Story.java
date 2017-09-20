package com.example.client;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Story {

  public static Builder builder() {
    return new Builder();
  }

  private String title;
  private Author author;
  private String body;
  private List<Tag> tags;
  private Instant publishTime;

  private Story(String title, Author author, String body, List<Tag> tags, Instant publishTime) {
    this.title = title;
    this.author = author;
    this.body = body;
    this.tags = tags;
    this.publishTime = publishTime;
  }

  public String getTitle() {
    return title;
  }

  public Author getAuthor() {
    return author;
  }

  public String getBody() {
    return body;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public Instant getPublishTime() {
    return publishTime;
  }

  public static class Builder {

    private String title;
    private Author author;
    private String body;
    private List<Tag> tags = new ArrayList<>();
    private Instant publishedAt;

    public Builder withTitle(String title) {
      this.title = title;

      return this;
    }

    public Builder withAuthor(Function<Author.Builder, Author> authorBuilder) {
      this.author = authorBuilder.apply(Author.builder());

      return this;
    }

    public Builder withBody(String body) {
      this.body = body;

      return this;
    }

    public Builder withTag(Tag tag) {
      this.tags.add(tag);

      return this;
    }

    public Builder withTag(String tagName) {
      this.tags.add(new Tag(tagName));

      return this;
    }

    public Builder publishAt(Instant publishedAt) {
      this.publishedAt = publishedAt;

      return this;
    }

    public Builder publishNow() {
      this.publishedAt = Instant.now();

      return this;
    }

    public Story build() {
      return new Story(title, author, body, tags, publishedAt);
    }
  }
}
