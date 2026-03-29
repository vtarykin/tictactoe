package org.example.tictactoe.domain.model;

import java.util.UUID;

public class CurrentGame {
  private final UUID id;
  private final GameBoard board;

  public CurrentGame() {
    this(UUID.randomUUID()); // старый конструктор
  }

  public CurrentGame(UUID id) {
    this.id = id;
    this.board = new GameBoard();
  }

  public UUID getId() {
    return id;
  }

  public GameBoard getBoard() {
    return board;
  }
}