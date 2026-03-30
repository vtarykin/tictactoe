package org.example.tictactoe.datasource.mapper;

import org.example.tictactoe.datasource.model.CurrentGameEntity;
import org.example.tictactoe.datasource.model.GameBoardEntity;
import org.example.tictactoe.domain.model.CurrentGame;
import org.example.tictactoe.domain.model.GameBoard;

public class CurrentGameMapper {

  public CurrentGame toDomain(CurrentGameEntity entity) {
    GameBoard board = new GameBoard();
    board.setBoard(entity.getBoard().getBoard());

    return new CurrentGame(entity.getId(), board);
  }

  public CurrentGameEntity toEntity(CurrentGame game) {
    GameBoardEntity boardEntity =
        new GameBoardEntity(game.getBoard().getBoard());

    return new CurrentGameEntity(game.getId(), boardEntity);
  }
}