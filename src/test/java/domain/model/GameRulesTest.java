package test.java.domain.model;

import main.java.domain.model.GameBoard;
import main.java.domain.model.GameRules;
import main.java.domain.model.GameStatus;
import main.java.domain.model.Move;
import main.java.domain.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameRulesTest {

  private final GameRules rules = new GameRules();

  @Test
  void shouldDetectRowWinner() {

    GameBoard board = new GameBoard();

    board.applyMove(new Move(0,0, Player.X));
    board.applyMove(new Move(0,1, Player.X));
    board.applyMove(new Move(0,2, Player.X));

    assertEquals(GameStatus.X_WON, rules.evaluate(board));
  }

  @Test
  void shouldDetectColumnWinner() {

    GameBoard board = new GameBoard();

    board.applyMove(new Move(0,0, Player.O));
    board.applyMove(new Move(1,0, Player.O));
    board.applyMove(new Move(2,0, Player.O));

    assertEquals(GameStatus.O_WON, rules.evaluate(board));
  }

  @Test
  void shouldDetectDraw() {

    GameBoard board = new GameBoard();

    Player[][] moves = {
        {Player.X, Player.O, Player.X},
        {Player.X, Player.X, Player.O},
        {Player.O, Player.X, Player.O}
    };

    for(int r=0;r<3;r++){
      for(int c=0;c<3;c++){
        board.applyMove(new Move(r,c,moves[r][c]));
      }
    }

    assertEquals(GameStatus.DRAW, rules.evaluate(board));
  }
}