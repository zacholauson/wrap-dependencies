package com.example.client;

import java.time.Instant;
import java.util.List;

public class Story {

  private String title;
  private Author author;
  private String body;
  private List<Tag> tags;
  private Instant publishTime;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Instant getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(Instant publishTime) {
    this.publishTime = publishTime;
  }
}
