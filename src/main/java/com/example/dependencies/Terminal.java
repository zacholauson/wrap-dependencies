package com.example.dependencies;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Terminal {

  private static final String HEADER_FORMAT = "| %1$-30s | %2$-20s | %3$-24s |\n";
  private static final String BODY_FORMAT = "| %1$-80s |\n";
  private static final String SITE_NAME_HEADER_FORMAT = "| SITE: %1$-74s |\n";

  public void printHeader(String siteName) {
    System.out.format(SITE_NAME_HEADER_FORMAT, siteName);
  }

  public void printSeparator() {
    System.out.format(BODY_FORMAT, "--------------------------------------------------------------------------------");
  }

  public void printStory(String title, String authorFirstName, String authorLastName, String body, List<String> tags, Instant publishTime) {
    System.out.format(HEADER_FORMAT, title, authorFirstName + " " + authorLastName, publishTime);
    System.out.format(BODY_FORMAT, body.substring(0, Math.min(body.length(), 75)) + "....");
    System.out.format(BODY_FORMAT, "Tags: " + tags.stream().collect(Collectors.joining(", ")));
  }
}
