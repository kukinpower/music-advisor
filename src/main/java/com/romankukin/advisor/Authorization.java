package com.romankukin.advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Authorization {

  private static final int DELAY = 1;
  private static final int PORT = 8080;
  private static final String GRANT_TYPE = "authorization_code";
  private String code;

  private HttpServer server;

  public Authorization() {
    this.code = "";
  }

  public boolean create() {
    try {
      server = HttpServer.create();
      server.bind(new InetSocketAddress(PORT), 0);
      server.createContext("/", new HttpHandler() {
        public void handle(HttpExchange exchange) throws IOException {

          String query = exchange.getRequestURI().getQuery();

          String response = "Authorization code not found. Try again.";
          if (query != null && query.startsWith("code=")) {
            code = query.substring(5);

            response = "Got the code. Return back to your program.";
          }

          exchange.sendResponseHeaders(200, response.length());
          exchange.getResponseBody().write(response.getBytes());
          exchange.getResponseBody().close();
        }
      });
    } catch (IOException  e) {
      e.printStackTrace();
      return false;
    }

    server.start();
    System.out.println("waiting for code...");

    while ("".equals(code)) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
        return false;
      }
    }
    System.out.println("code received");

    accessToken();
    server.stop(DELAY);
    return true;
  }

  public void accessToken() {
    System.out.println("making http request for access_token...");
    System.out.println("response:");
    HttpRequest request = HttpRequest.newBuilder()
        .header("Content-Type", "application/x-www-form-urlencoded")
        .uri(URI.create(Application.authServer() + "/api/token"))
        .POST(HttpRequest.BodyPublishers.ofString(
            "grant_type=" + GRANT_TYPE
                + "&code=" + code
                + "&client_id=" + Application.CLIENT_ID
                + "&client_secret=" + Application.CLIENT_SECRET
                + "&redirect_uri=" + Application.REDIRECT_URI))
        .build();

    try {
      HttpClient client = HttpClient.newBuilder().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      assert response != null;
      System.out.println(response.body());
      System.out.println( "\n---SUCCESS---");
    } catch (InterruptedException | IOException e) { System.out.println("Error response"); }
  }

}
