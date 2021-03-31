package com.romankukin.advisor;

import java.util.Scanner;

public class Application {

  private static String AUTH_SERVER = "https://accounts.spotify.com";
  public static final String REDIRECT_URI = "http://localhost:8080";
  public static final String CLIENT_ID = "06af5b86f3d6401e961a5c61bd96b7ab";
  public static final String CLIENT_SECRET = "f586adf76ee94cf99797a9433a42a12d";

  Application() {
  }

  Application(String authServer) {
    AUTH_SERVER = authServer;
  }

  public static String authServer() {
    return AUTH_SERVER;
  }

  public void run() {

    try (Scanner scanner = new Scanner(System.in)) {

      Advisor advisor = new AuthorizedAdvisor(new SimpleAdvisor());
      String command;
      do {
        command = scanner.next();
        switch (command) {
          case "playlists":
            advisor.playlists(scanner.next());
            break;
          case "new":
            advisor.releases();
            break;
          case "auth":
            if (!advisor.authorised()) {
              advisor.auth();
            }
            break;
          case "featured":
            advisor.featured();
            break;
          case "categories":
            advisor.categories();
            break;
        }
      }
      while (!"exit".equals(command));

    } catch (Exception | Error e) {
      System.out.println(e.getMessage());
    }
    System.out.println("---GOODBYE!---");
  }
}
