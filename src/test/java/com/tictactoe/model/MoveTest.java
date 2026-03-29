package test.java.domain.model;

import main.java.com.tictactoe.domain.model.Move;
import main.java.com.tictactoe.domain.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

  @Test
  void moveShouldStoreCoordinatesAndPlayer() {

    Move move = new Move(1, 2, Player.X);

    assertEquals(1, move.getRow());
    assertEquals(2, move.getCol());
    assertEquals(Player.X, move.getPlayer());

  }

}
