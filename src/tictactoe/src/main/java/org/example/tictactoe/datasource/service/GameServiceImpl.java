package org.example.tictactoe.datasource.service;

import org.example.tictactoe.domain.model.CurrentGame;
import org.example.tictactoe.domain.service.GameService;
import org.example.tictactoe.datasource.repository.CurrentGameRepository;

public class GameServiceImpl implements GameService {

  private final CurrentGameRepository repository;

  public GameServiceImpl(CurrentGameRepository repository) {
    this.repository = repository;
  }

  @Override
  public String getGameResult(CurrentGame game) {
    int result = evaluate(game.getBoard().getBoard());

    if (result == 10) return "Computer wins";
    if (result == -10) return "Player wins";
    if (isFull(game.getBoard().getBoard())) return "Draw";

    return "In progress";
  }

  @Override
  public CurrentGame nextMove(CurrentGame game) {
    int[][] board = game.getBoard().getBoard();

    if (evaluate(board) != 0 || isFull(board)) {
      return game;
    }

    int bestScore = Integer.MIN_VALUE;
    int bestRow = -1;
    int bestCol = -1;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == 0) {
          board[i][j] = 2; // ход компьютера

          int score = minimax(board, 0, false);

          board[i][j] = 0;

          if (score > bestScore) {
            bestScore = score;
            bestRow = i;
            bestCol = j;
          }
        }
      }
    }

    if (bestRow != -1) {
      board[bestRow][bestCol] = 2;
    }

    repository.save(game);
    return game;
  }

  private int minimax(int[][] board, int depth, boolean isMaximizing) {
    int result = evaluate(board);

    if (result != 0) return result;
    if (isFull(board)) return 0;

    if (isMaximizing) {
      int best = Integer.MIN_VALUE;

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (board[i][j] == 0) {
            board[i][j] = 2;
            best = Math.max(best, minimax(board, depth + 1, false));
            board[i][j] = 0;
          }
        }
      }

      return best;
    } else {
      int best = Integer.MAX_VALUE;

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (board[i][j] == 0) {
            board[i][j] = 1;
            best = Math.min(best, minimax(board, depth + 1, true));
            board[i][j] = 0;
          }
        }
      }

      return best;
    }
  }

  public int evaluate(int[][] b) {
    for (int i = 0; i < 3; i++) {
      if (b[i][0] == b[i][1] && b[i][1] == b[i][2]) {
        if (b[i][0] == 2) return 10;
        if (b[i][0] == 1) return -10;
      }

      if (b[0][i] == b[1][i] && b[1][i] == b[2][i]) {
        if (b[0][i] == 2) return 10;
        if (b[0][i] == 1) return -10;
      }
    }

    if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
      if (b[0][0] == 2) return 10;
      if (b[0][0] == 1) return -10;
    }

    if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
      if (b[0][2] == 2) return 10;
      if (b[0][2] == 1) return -10;
    }

    return 0;
  }

  private boolean isFull(int[][] board) {
    for (int[] row : board) {
      for (int cell : row) {
        if (cell == 0) return false;
      }
    }
    return true;
  }

  @Override
  public boolean isGameOver(CurrentGame game) {
    int[][] board = game.getBoard().getBoard();

    return evaluate(board) != 0 || isFull(board);
  }

  @Override
  public boolean validateMove(CurrentGame oldGame, CurrentGame newGame) {
    int[][] oldBoard = oldGame.getBoard().getBoard();
    int[][] newBoard = newGame.getBoard().getBoard();

    int diffCount = 0;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int oldCell = oldBoard[i][j];
        int newCell = newBoard[i][j];

        if (oldCell != 0 && oldCell != newCell) {
          return false;
        }

        if (oldCell == 0 && newCell == 1) {
          diffCount++;
        }


        if (newCell < 0 || newCell > 2) {
          return false;
        }
      }
    }


    return diffCount == 1;
  }
}