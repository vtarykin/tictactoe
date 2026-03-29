package main.java.web.model;

public class GameBoardDTO {

  private int[][] cells;

  public GameBoardDTO() {
  }

  public GameBoardDTO(int[][] cells) {
    this.cells = cells;
  }

  public int[][] getCells() {
    return cells;
  }

  public void setCells(int[][] cells) {
    this.cells = cells;
  }
}