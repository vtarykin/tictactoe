package main.java.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

  private static final int SIZE = 3;

  private final Player[][] board;

  public GameBoard() {
    this.board = new Player[SIZE][SIZE];
  }

  private GameBoard(Player[][] board) {
    this.board = new Player[SIZE][SIZE];
    for (int r = 0; r < SIZE; r++) {
      this.board[r] = Arrays.copyOf(board[r], SIZE);
    }
  }

  public Player getCell(int row, int col) {
    validate(row, col);
    return board[row][col];
  }

  public boolean isEmpty(int row, int col) {
    validate(row, col);
    return board[row][col] == null;
  }

  public void applyMove(Move move) {

    int row = move.getRow();
    int col = move.getCol();

    validate(row, col);

    if (!isEmpty(row, col)) {
      throw new IllegalStateException("Cell already occupied");
    }

    board[row][col] = move.getPlayer();
  }

  public List<Move> getAvailableMoves(Player player) {

    List<Move> moves = new ArrayList<>();

    for (int r = 0; r < SIZE; r++) {
      for (int c = 0; c < SIZE; c++) {

        if (board[r][c] == null) {
          moves.add(new Move(r, c, player));
        }

      }
    }

    return moves;
  }

  public boolean isFull() {

    for (int r = 0; r < SIZE; r++) {
      for (int c = 0; c < SIZE; c++) {

        if (board[r][c] == null) {
          return false;
        }

      }
    }

    return true;
  }

  public int getSize() {
    return SIZE;
  }

  private void validate(int row, int col) {

    if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
      throw new IllegalArgumentException("Invalid position");
    }

  }

  public GameBoard copy() {
    return new GameBoard(this.board);
  }
}