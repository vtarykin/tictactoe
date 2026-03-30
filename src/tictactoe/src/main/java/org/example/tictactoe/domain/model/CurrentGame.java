package org.example.tictactoe.domain.model;

import java.util.UUID;

public class CurrentGame {
  private final UUID id;
  private final GameBoard board;

  public CurrentGame() {
    this(UUID.randomUUID(), new GameBoard());
  }

  public CurrentGame(UUID id) {
    this(id, new GameBoard());
  }

  public CurrentGame(UUID id, GameBoard board) {
    this.id = id;
    this.board = board;
  }

  public UUID getId() {
    return id;
  }

  public GameBoard getBoard() {
    return board;
  }
}