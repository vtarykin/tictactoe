package main.java.web.mapper;
import main.java.domain.model.CurrentGame;
import main.java.domain.model.GameStatus;
import main.java.domain.model.Player;
import main.java.web.model.CurrentGameDTO;

public class CurrentGameWebMapper {

  private final GameBoardWebMapper boardMapper = new GameBoardWebMapper();

  public CurrentGameDTO toDTO(CurrentGame game) {

    return new CurrentGameDTO(
        game.getId(),
        boardMapper.toDTO(game.getBoard()),
        game.getCurrentPlayer().name(),
        game.getStatus().name()
    );
  }

  public CurrentGame toDomain(CurrentGameDTO dto) {

    return new CurrentGame(
        dto.getId(),
        boardMapper.toDomain(dto.getBoard()),
        Player.valueOf(dto.getCurrentPlayer()),
        GameStatus.valueOf(dto.getStatus())
    );
  }
}