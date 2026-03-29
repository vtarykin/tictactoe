package org.example.tictactoe.datasource.repository;

import org.example.tictactoe.datasource.model.CurrentGameStorage;
import org.example.tictactoe.domain.model.CurrentGame;

import java.util.UUID;

public class CurrentGameRepository {

  private final CurrentGameStorage storage;

  public CurrentGameRepository(CurrentGameStorage storage) {
    this.storage = storage;
  }

  public void save(CurrentGame game) {
    storage.save(game);
  }

  public CurrentGame get(UUID id) {
    return storage.get(id);
  }
}