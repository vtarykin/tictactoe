package org.example.tictactoe.domain.model;

public class GameBoard {
  private final int[][] board;

  public GameBoard() {
    this.board = new int[3][3]; // 3x3 Tic-Tac-Toe
  }

  public int[][] getBoard() {
    return board;
  }

  public void setCell(int row, int col, int value) {
    board[row][col] = value; // 1 = player, 2 = computer
  }

  public int getCell(int row, int col) {
    return board[row][col];
  }
  public void setBoard(int[][] newBoard) {
    for (int i = 0; i < 3; i++) {
      System.arraycopy(newBoard[i], 0, this.board[i], 0, 3);
    }
  }
}