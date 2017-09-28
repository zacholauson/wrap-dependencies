package com.example.dependencies;

import com.example.client.Client;
import com.example.client.ClientAuthor;
import com.example.client.ClientStory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class AppTest {

  private Client client;
  private Terminal terminal;
  private App app;

  @Before
  public void setup() {
    client = Mockito.mock(Client.class);
    terminal = Mockito.mock(Terminal.class);

    app = new App(client, terminal);
  }

  @Test
  public void itAddsTheStoriesToTheClient() {
    String fakeSiteName = "www.example.com";

    String fakeStoryIdOne = "someFakeStoryIdOne";
    Story firstStory = Story.builder()
        .withTitle("fakeStoryOneTitle")
        .withAuthor(new Author("Jim", "Halpert"))
        .withBody("someFakeStoryOneBody")
        .withTags("fakeTagOne", "fakeTagTwo")
        .build();

    String fakeStoryIdTwo = "someFakeStoryIdTwo";
    Story secondStory = Story.builder()
        .withTitle("fakeStoryTwoTitle")
        .withAuthor(new Author("Michael", "Scott"))
        .withBody("someFakeStoryTwoBody")
        .withTags("fakeTagThree", "fakeTagFour")
        .build();

    Mockito.when(client.initializeNewStory(fakeSiteName))
        .thenReturn(fakeStoryIdOne)
        .thenReturn(fakeStoryIdTwo);

    app.addStories(fakeSiteName,
        ImmutableList.of(
            firstStory, secondStory));

    Mockito.verify(client).addStoryInformation(
        fakeStoryIdOne,
        firstStory.title(),
        firstStory.body(),
        firstStory.tags());
    Mockito.verify(client).addAuthorInformation(
        fakeStoryIdOne,
        firstStory.author().firstName(),
        firstStory.author().lastName());
    Mockito.verify(client).publishStory(fakeStoryIdOne);

    Mockito.verify(client).addStoryInformation(
        fakeStoryIdTwo,
        secondStory.title(),
        secondStory.body(),
        secondStory.tags());
    Mockito.verify(client).addAuthorInformation(
        fakeStoryIdTwo,
        secondStory.author().firstName(),
        secondStory.author().lastName());
    Mockito.verify(client).publishStory(fakeStoryIdTwo);
  }

  @Test
  public void itPrintsStoriesFromClient() {
    String clientSiteName = "www.foobar.com";

    String clientAuthorFirstName = "Dwight";
    String clientAuthorLastName = "Schrute";

    ClientAuthor mockClientAuthor =
        Mockito.mock(ClientAuthor.class);
    Mockito.when(mockClientAuthor.firstName())
        .thenReturn(clientAuthorFirstName);
    Mockito.when(mockClientAuthor.lastName())
        .thenReturn(clientAuthorLastName);

    String clientStoryTitle = "fakeStoryTitle";
    String clientStoryBody = "fakeStoryBody";
    List<String> clientStoryTags = ImmutableList.of("fakeTagTwo", "fakeTagTwo");
    Instant clientStoryPublishDate = Instant.now().plus(Duration.ofHours(5));

    ClientStory mockClientStory =
        Mockito.mock(ClientStory.class);
    Mockito.when(mockClientStory.title())
        .thenReturn(clientStoryTitle);
    Mockito.when(mockClientStory.author())
        .thenReturn(mockClientAuthor);
    Mockito.when(mockClientStory.body())
        .thenReturn(clientStoryBody);
    Mockito.when(mockClientStory.tags())
        .thenReturn(clientStoryTags);
    Mockito.when(mockClientStory.publishedAt())
        .thenReturn(clientStoryPublishDate);

    Mockito.when(client.stories())
        .thenReturn(ImmutableMap.of(
            clientSiteName, ImmutableList.of(mockClientStory)));

    app.printStories();

    Mockito.verify(terminal).printHeader(clientSiteName);
    Mockito.verify(terminal, Mockito.times(2)).printSeparator();
    Mockito.verify(terminal).printStory(
        clientStoryTitle,
        clientAuthorFirstName,
        clientAuthorLastName,
        clientStoryBody,
        clientStoryTags,
        clientStoryPublishDate);
  }
}