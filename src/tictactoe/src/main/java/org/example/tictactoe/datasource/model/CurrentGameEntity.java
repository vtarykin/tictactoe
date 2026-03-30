package org.example.tictactoe.datasource.model;

import java.util.UUID;

public class CurrentGameEntity {
  private UUID id;
  private GameBoardEntity board;

  public CurrentGameEntity(UUID id, GameBoardEntity board) {
    this.id = id;
    this.board = board;
  }

  public UUID getId() {
    return id;
  }

  public GameBoardEntity getBoard() {
    return board;
  }
}