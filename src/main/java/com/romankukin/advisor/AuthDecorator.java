package com.romankukin.advisor;

public abstract class AuthDecorator implements Advisor {
  abstract void notAuthorisedMessage();
}
