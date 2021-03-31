package com.romankukin.advisor;

public class AuthorizedAdvisor extends AuthDecorator {

  private final String NOT_AUTHORISED = "Please, provide access for application.";

  private final Advisor advisor;
  private boolean status;

  public AuthorizedAdvisor(Advisor advisor) {
    this.advisor = advisor;
    this.status = false;
    this.authorization = new Authorization();
  }

  @Override
  public void releases() {
    if (status) {
      advisor.releases();
    } else {
      notAuthorisedMessage();
    }
  }

  @Override
  public void featured() {
    if (status) {
      advisor.featured();
    } else {
      notAuthorisedMessage();
    }
  }

  @Override
  public void categories() {
    if (status) {
      advisor.categories();
    } else {
      notAuthorisedMessage();
    }
  }

  @Override
  public void playlists(String command) {
    if (status) {
      advisor.playlists(command);
    } else {
      notAuthorisedMessage();
    }
  }

  private Authorization authorization;

  public boolean startServer() {
    authorization = new Authorization();

    boolean status = authorization.create();

    return status;
  }

  @Override
  public void auth() {
    if (!status) {
      advisor.auth();
      if (authorization.create()) {
        status = true;
      }
    } else {
      System.out.println("Already authorised");
    }
  }

  @Override
  protected void notAuthorisedMessage() {
    System.out.println(NOT_AUTHORISED);
  }

  @Override
  public boolean authorised() {
    return status;
  }
}
