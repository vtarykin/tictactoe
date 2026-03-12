package test.java.domain.model;
import org.junit.jupiter.api.Test;
import main.java.domain.model.*;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

  @Test
  void testApplyMoveAndGetCell() {
    GameBoard board = new GameBoard();
    Move move = new Move(0, 0, Player.X);
    board.applyMove(move);

    assertEquals(Player.X, board.getCell(0,0));
    assertTrue(board.isEmpty(0,1));
  }

  @Test
  void testApplyMoveToOccupiedCellThrows() {
    GameBoard board = new GameBoard();
    board.applyMove(new Move(0,0, Player.X));

    assertThrows(IllegalStateException.class,
        () -> board.applyMove(new Move(0,0, Player.O)));
  }

  @Test
  void testCopyBoard() {
    GameBoard board = new GameBoard();
    board.applyMove(new Move(0,0, Player.X));
    GameBoard copy = board.copy();

    assertEquals(Player.X, copy.getCell(0,0));
    copy.applyMove(new Move(1,1, Player.O));
    assertNull(board.getCell(1,1));  // оригинальная доска не изменилась
  }

}