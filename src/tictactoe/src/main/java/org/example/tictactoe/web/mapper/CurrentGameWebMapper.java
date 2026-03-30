package org.example.tictactoe.web.mapper;

import org.example.tictactoe.domain.model.CurrentGame;
import org.example.tictactoe.domain.model.GameBoard;
import org.example.tictactoe.web.model.CurrentGameDTO;
import org.example.tictactoe.web.model.GameBoardDTO;

public class CurrentGameWebMapper {

  public CurrentGameDTO toDTO(CurrentGame game) {
    CurrentGameDTO dto = new CurrentGameDTO();
    dto.setId(game.getId());

    GameBoardDTO boardDTO = new GameBoardDTO();
    int[][] src = game.getBoard().getBoard();
    int[][] dest = new int[3][3];
    for (int i = 0; i < 3; i++) {
      System.arraycopy(src[i], 0, dest[i], 0, 3);
    }
    boardDTO.setBoard(dest);
    dto.setBoard(boardDTO);

    return dto;
  }

  public CurrentGame toDomain(CurrentGameDTO dto) {
    int[][] src = dto.getBoard().getBoard();

    GameBoard board = new GameBoard();
    int[][] dest = board.getBoard();

    for (int i = 0; i < 3; i++) {
      System.arraycopy(src[i], 0, dest[i], 0, 3);
    }

    return new CurrentGame(dto.getId(), board);
  }
}