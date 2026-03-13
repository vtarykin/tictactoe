package main.java.web.model;

import java.util.UUID;

public class CurrentGameDTO {

  private UUID id;
  private GameBoardDTO board;
  private String currentPlayer;
  private String status;

  public CurrentGameDTO() {
  }

  public CurrentGameDTO(UUID id, GameBoardDTO board, String currentPlayer, String status) {
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

  public GameBoardDTO getBoard() {
    return board;
  }

  public void setBoard(GameBoardDTO board) {
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
