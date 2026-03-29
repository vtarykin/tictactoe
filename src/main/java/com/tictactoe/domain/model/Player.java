package main.java.domain.model;

public enum Player {
  X,
  O;

  public Player opponent() {
    return this == X ? O : X;
  }
}
