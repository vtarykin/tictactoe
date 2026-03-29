package org.example.tictactoe.datasource.model;

import org.example.tictactoe.domain.model.CurrentGame;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentGameStorage {

  private final Map<UUID, CurrentGame> games = new ConcurrentHashMap<>();

  public void save(CurrentGame game) {
    games.put(game.getId(), game);
  }

  public CurrentGame get(UUID id) {
    return games.get(id);
  }
}