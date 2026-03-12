package domain.model;

public class GameBoard {
  private int rows;
  private int cols;
  private int[][] gameBoard;
  private int value;
  public GameBoard() {
    this.rows = 3;
    this.cols = 3;
    this.gameBoard = new int[this.rows][this.cols];
  }
  public GameBoard(int rows, int cols) {
    if (rows < 3 || cols < 3) {
      throw new IllegalArgumentException("Board size must be at least 3x3");
    }
    this.rows = rows;
    this.cols = cols;
    this.gameBoard = new int[rows][cols];
  }

  public void setValue(int rows, int cols, int value) {
    this.gameBoard[rows][cols] = value;
  }

  public int getValue(int rows, int cols) {
    return gameBoard[rows][cols];
  }

//  applyMove(Move) {
//    return;
//  }
  //  public void setRows(int rows) {
//    this.rows = rows;
//  }
}
