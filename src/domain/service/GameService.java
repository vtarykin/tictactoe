package domain.service;
//import domain.model.GameBoard;

public interface GameService {
  int nextMove();
  boolean validateCurGB();
  boolean checkEndGame();
}
