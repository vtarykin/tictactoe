package main.java.datasource.model;

import java.util.UUID;

public class CurrentGameEntity {

  private UUID id;
  private GameBoardEntity board;
  private String currentPlayer;
  private String status;

  public CurrentGameEntity() {
  }

  public CurrentGameEntity(UUID id, GameBoardEntity board, String currentPlayer, String status) {
    this.id = id;
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.status = status;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public GameBoardEntity getBoard() {
    return board;
  }

  public void setBoard(GameBoardEntity board) {
    this.board = board;
  }

  public String getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(String currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}