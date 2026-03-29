package main.java.datasource.model;

public class GameBoardEntity {

  private int[][] cells;

  public GameBoardEntity() {
  }

  public GameBoardEntity(int[][] cells) {
    this.cells = cells;
  }

  public int[][] getCells() {
    return cells;
  }

  public void setCells(int[][] cells) {
    this.cells = cells;
  }
}