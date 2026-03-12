package main.java.domain.model;

public class GameRules {

  public Player checkWinner(GameBoard board) {
    int size = board.getSize();

    // Проверка строк
    for (int r = 0; r < size; r++) {
      Player first = board.getCell(r, 0);
      if (first != null &&
          first == board.getCell(r, 1) &&
          first == board.getCell(r, 2)) {
        return first;
      }
    }

    // Проверка колонок
    for (int c = 0; c < size; c++) {
      Player first = board.getCell(0, c);
      if (first != null &&
          first == board.getCell(1, c) &&
          first == board.getCell(2, c)) {
        return first;
      }
    }

    // Проверка диагонали
    Player center = board.getCell(1, 1);
    if (center != null) {
      if (center == board.getCell(0, 0) &&
          center == board.getCell(2, 2)) {
        return center;
      }
      // анти-диагональ
      if (center == board.getCell(0, 2) &&
          center == board.getCell(2, 0)) {
        return center;
      }
    }

    return null;
  }

  public boolean isDraw(GameBoard board) {
    return board.isFull() && checkWinner(board) == null;
  }

  public GameStatus evaluate(GameBoard board) {
    Player winner = checkWinner(board);

    if (winner == null) {
      return board.isFull() ? GameStatus.DRAW : GameStatus.IN_PROGRESS;
    }

    return winner == Player.X ? GameStatus.X_WON : GameStatus.O_WON;
  }
}