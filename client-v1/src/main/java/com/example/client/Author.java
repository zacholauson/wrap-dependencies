package com.example.client;

public class Author {

  public static Builder builder() {
    return new Builder();
  }

  private final String firstName;
  private final String lastName;
  private final String location;

  private Author(String firstName, String lastName, String location) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.location = location;
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  public String location() {
    return location;
  }

  public static class Builder {

    private String firstName;
    private String lastName;
    private String location;

    private Builder() { }

    public Builder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Author build() {
      return new Author(firstName, lastName, location);
    }
  }
}
