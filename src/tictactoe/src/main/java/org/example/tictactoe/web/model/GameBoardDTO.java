package org.example.tictactoe.web.model;

public class GameBoardDTO {
  private int[][] board;

  public GameBoardDTO() {
    this.board = new int[3][3];
  }

  public int[][] getBoard() {
    return board;
  }

  public void setBoard(int[][] board) {
    this.board = board;
  }
}