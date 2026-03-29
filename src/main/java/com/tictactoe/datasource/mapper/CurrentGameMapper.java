package main.java.datasource.mapper;

import main.java.datasource.model.CurrentGameEntity;
import main.java.datasource.model.GameBoardEntity;
import main.java.domain.model.CurrentGame;
import main.java.domain.model.GameStatus;
import main.java.domain.model.Player;

public class CurrentGameMapper {

  private final GameBoardMapper boardMapper = new GameBoardMapper();

  public CurrentGameEntity toEntity(CurrentGame game) {

    GameBoardEntity boardEntity = boardMapper.toEntity(game.getBoard());

    return new CurrentGameEntity(
        game.getId(),
        boardEntity,
        game.getCurrentPlayer().name(),
        game.getStatus().name()
    );
  }

  public CurrentGame toDomain(CurrentGameEntity entity) {

    return new CurrentGame(
        entity.getId(),
        boardMapper.toDomain(entity.getBoard()),
        Player.valueOf(entity.getCurrentPlayer()),
        GameStatus.valueOf(entity.getStatus())
    );
  }
}