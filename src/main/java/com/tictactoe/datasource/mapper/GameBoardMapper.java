package main.java.datasource.mapper;

import main.java.datasource.model.GameBoardEntity;
import main.java.domain.model.GameBoard;
import main.java.domain.model.Player;

public class GameBoardMapper {

  public GameBoardEntity toEntity(GameBoard board) {

    int size = board.getSize();
    int[][] cells = new int[size][size];

    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {

        Player player = board.getCell(r, c);

        if (player == null) {
          cells[r][c] = 0;
        } else if (player == Player.X) {
          cells[r][c] = 1;
        } else {
          cells[r][c] = 2;
        }
      }
    }

    return new GameBoardEntity(cells);
  }

  public GameBoard toDomain(GameBoardEntity entity) {

    GameBoard board = new GameBoard();
    int[][] cells = entity.getCells();

    for (int r = 0; r < cells.length; r++) {
      for (int c = 0; c < cells[r].length; c++) {

        if (cells[r][c] == 1) {
          board.applyMove(new main.java.domain.model.Move(r, c, Player.X));
        }

        if (cells[r][c] == 2) {
          board.applyMove(new main.java.domain.model.Move(r, c, Player.O));
        }
      }
    }

    return board;
  }
}