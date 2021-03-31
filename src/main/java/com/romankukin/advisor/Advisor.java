package com.romankukin.advisor;

public interface Advisor {

  public void releases();

  public void featured();

  public void categories();

  public void playlists(String command);

  public void auth();

  public boolean authorised();
}
