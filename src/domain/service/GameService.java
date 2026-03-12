package domain.service;
import domain.model.CurrentGame;

public interface GameService {
  CurrentGame getNextMove();
  boolean validateCurGB();
  boolean checkEndGame();
}
