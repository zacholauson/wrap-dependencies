package com.example.dependencies;

public class Author {

  private final String firstName;
  private final String lastName;

  public Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }
}
