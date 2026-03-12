package test.java.domain.model;

import main.java.domain.model.CurrentGame;
import main.java.domain.model.GameBoard;
import main.java.domain.model.GameStatus;
import main.java.domain.model.Move;
import main.java.domain.model.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CurrentGameTest {

  @Test
  void shouldSwitchPlayer() {

    CurrentGame game = new CurrentGame(
        UUID.randomUUID(),
        new GameBoard(),
        Player.X,
        GameStatus.IN_PROGRESS
    );

    game.switchPlayer();

    assertEquals(Player.O, game.getCurrentPlayer());
  }

  @Test
  void copyShouldNotModifyOriginalGame() {

    CurrentGame game = new CurrentGame(
        UUID.randomUUID(),
        new GameBoard(),
        Player.X,
        GameStatus.IN_PROGRESS
    );

    game.getBoard().applyMove(new Move(0,0, Player.X));

    CurrentGame copy = game.copy();

    copy.getBoard().applyMove(new Move(1,1, Player.O));

    assertNull(game.getBoard().getCell(1,1));
  }
}
