package org.example.tictactoe.domain.service;

import org.example.tictactoe.domain.model.CurrentGame;

public interface GameService {
  CurrentGame nextMove(CurrentGame game);
  int evaluate(int[][] board);
  boolean validateBoard(CurrentGame game);
  boolean isGameOver(CurrentGame game);
  boolean validateMove(CurrentGame oldGame, CurrentGame newGame);
}