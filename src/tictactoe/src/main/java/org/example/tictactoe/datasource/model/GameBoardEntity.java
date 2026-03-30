package org.example.tictactoe.datasource.model;

public class GameBoardEntity {
  private int[][] board;

  public GameBoardEntity(int[][] board) {
    this.board = board;
  }

  public int[][] getBoard() {
    return board;
  }

  public void setBoard(int[][] board) {
    this.board = board;
  }
}