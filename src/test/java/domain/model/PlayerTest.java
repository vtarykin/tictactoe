package test.java.domain.model;

import main.java.domain.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  @Test
  void opponentShouldReturnCorrectPlayer() {

    assertEquals(Player.O, Player.X.opponent());
    assertEquals(Player.X, Player.O.opponent());

  }

}