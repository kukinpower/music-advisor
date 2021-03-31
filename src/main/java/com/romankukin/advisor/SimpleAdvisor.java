package com.romankukin.advisor;

import java.util.Locale;

public class SimpleAdvisor implements Advisor {

  public SimpleAdvisor() {
  }

  @Override
  public void releases() {
    System.out.println("---NEW RELEASES---\n"
        + "Mountains [Sia, Diplo, Labrinth]\n"
        + "Runaway [Lil Peep]\n"
        + "The Greatest Show [Panic! At The Disco]\n"
        + "All Out Life [Slipknot]");
  }

  @Override
  public void featured() {
    System.out.println("---FEATURED---\n"
        + "Mellow Morning\n"
        + "Wake Up and Smell the Coffee\n"
        + "Monday Motivation\n"
        + "Songs to Sing in the Shower");
  }

  @Override
  public void categories() {
    System.out.println("---CATEGORIES---\n"
        + "Top Lists\n"
        + "Pop\n"
        + "Mood\n"
        + "Latin");
  }

  @Override
  public void playlists(String genre) {
    System.out.println("---"
        + genre.toUpperCase(Locale.ROOT)
        + " PLAYLISTS---");
    System.out.println("Walk Like A Badass  \n"
        + "Rage Beats  \n"
        + "Arab Mood Booster  \n"
        + "Sunday Stroll");
  }

  @Override
  public void auth() {
    System.out.println("use this link to request the access code:");
    System.out.println(Application.authServer() + "/authorize?"
        + "client_id" + "=" + Application.CLIENT_ID
        + "&redirect_uri=" + Application.REDIRECT_URI
        + "&response_type=code");
  }

  @Override
  public boolean authorised() {
    return false;
  }
}
