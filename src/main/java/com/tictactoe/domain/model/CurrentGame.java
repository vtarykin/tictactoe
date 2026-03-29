package main.java.domain.model;

import java.util.UUID;

public class CurrentGame {

  private UUID id;
  private GameBoard board;
  private Player currentPlayer;
  private GameStatus status;

  public CurrentGame(UUID id, GameBoard board, Player currentPlayer, GameStatus status) {
    this.id = id;
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.status = status;
  }

  public UUID getId() {
    return id;
  }

  public GameBoard getBoard() {
    return board;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public GameStatus getStatus() {
    return status;
  }

  public void setStatus(GameStatus status) {
    this.status = status;
  }

  public void switchPlayer() {
    currentPlayer = currentPlayer.opponent();
  }

  public CurrentGame copy() {
    return new CurrentGame(
        this.id,                // UUID можно оставить тот же
        this.board.copy(),       // копируем доску
        this.currentPlayer,
        this.status
    );
  }
}