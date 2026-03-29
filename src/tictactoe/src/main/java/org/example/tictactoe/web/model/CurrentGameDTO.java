package org.example.tictactoe.web.model;

import java.util.UUID;

public class CurrentGameDTO {
  private UUID id;
  private GameBoardDTO board;

  public CurrentGameDTO() {}

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public GameBoardDTO getBoard() {
    return board;
  }

  public void setBoard(GameBoardDTO board) {
    this.board = board;
  }
}