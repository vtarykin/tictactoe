package main.java.domain.service;
import main.java.domain.model.CurrentGame;

public interface GameService {
  CurrentGame getNextMove(CurrentGame game);
  boolean validateBoard(CurrentGame oldGame, CurrentGame newGame);
  boolean isGameFinished(CurrentGame game);
}
