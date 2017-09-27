package com.example.client;

public class Author {

  static Builder builder() {
    return new Builder();
  }

  private final String firstName;
  private final String lastName;

  private Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  static class Builder {

    private String firstName;
    private String lastName;

    private Builder() { }

    Builder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    Builder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    Author build() {
      return new Author(firstName, lastName);
    }
  }
}
