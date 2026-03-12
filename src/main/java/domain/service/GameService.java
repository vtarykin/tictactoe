package main.java.domain.service;
import main.java.domain.model.CurrentGame;

public interface GameService {
  CurrentGame getNextMove();
  boolean validateCurGB();
  boolean checkEndGame();
}
