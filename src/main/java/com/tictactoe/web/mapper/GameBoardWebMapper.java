package main.java.web.mapper;

import main.java.domain.model.GameBoard;
import main.java.domain.model.Move;
import main.java.domain.model.Player;
import main.java.web.model.GameBoardDTO;

public class GameBoardWebMapper {

  public GameBoardDTO toDTO(GameBoard board) {

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

    return new GameBoardDTO(cells);
  }

  public GameBoard toDomain(GameBoardDTO dto) {

    GameBoard board = new GameBoard();
    int[][] cells = dto.getCells();

    for (int r = 0; r < cells.length; r++) {
      for (int c = 0; c < cells[r].length; c++) {

        if (cells[r][c] == 1) {
          board.applyMove(new Move(r, c, Player.X));
        }

        if (cells[r][c] == 2) {
          board.applyMove(new Move(r, c, Player.O));
        }
      }
    }

    return board;
  }
}