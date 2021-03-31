package com.romankukin.advisor;

public class Main {

  public static void main(String[] args) {

    Application app;

    if (args.length == 2 && "-access".equals(args[0])) {
      app = new Application(args[1]);
    } else {
      app = new Application();
    }

    app.run();
  }
}
