package main.java.domain.service;

import main.java.datasource.mapper.CurrentGameMapper;
import main.java.datasource.model.CurrentGameEntity;
import main.java.datasource.repository.GameRepository;
import main.java.domain.model.CurrentGame;
import main.java.domain.model.GameRules;
import main.java.domain.model.GameStatus;
import main.java.domain.model.Move;
import main.java.domain.model.Player;

public class GameServiceImpl implements GameService {

  private final GameRepository repository;
  private final CurrentGameMapper mapper;
  private final GameRules rules;

  public GameServiceImpl(GameRepository repository) {
    this.repository = repository;
    this.mapper = new CurrentGameMapper();
    this.rules = new GameRules();
  }

  @Override
  public CurrentGame getNextMove(CurrentGame game) {

    // AI пока делает первый доступный ход
    for (int r = 0; r < game.getBoard().getSize(); r++) {
      for (int c = 0; c < game.getBoard().getSize(); c++) {

        if (game.getBoard().isEmpty(r, c)) {

          Move move = new Move(r, c, game.getCurrentPlayer());
          game.getBoard().applyMove(move);

          game.setStatus(rules.evaluate(game.getBoard()));
          game.switchPlayer();

          CurrentGameEntity entity = mapper.toEntity(game);
          repository.save(entity);

          return game;
        }
      }
    }

    return game;
  }

  @Override
  public boolean validateBoard(CurrentGame oldGame, CurrentGame newGame) {

    int changes = 0;

    for (int r = 0; r < oldGame.getBoard().getSize(); r++) {
      for (int c = 0; c < oldGame.getBoard().getSize(); c++) {

        Player oldCell = oldGame.getBoard().getCell(r, c);
        Player newCell = newGame.getBoard().getCell(r, c);

        if (oldCell != newCell) {

          if (oldCell != null) {
            return false;
          }

          if (newCell != oldGame.getCurrentPlayer()) {
            return false;
          }

          changes++;
        }
      }
    }

    return changes == 1;
  }

  @Override
  public boolean isGameFinished(CurrentGame game) {

    GameStatus status = rules.evaluate(game.getBoard());

    return status == GameStatus.X_WON
        || status == GameStatus.O_WON
        || status == GameStatus.DRAW;
  }
}
