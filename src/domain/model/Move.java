package domain.model;

public class Move {
  int row;
  int col;
  Player player;

  public Move(int row, int col, Player player) {

    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid move");
    }

    this.row = row;
    this.col = col;
    this.player = player;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public Player getPlayer() {
    return player;
  }
}
