package org.example.tictactoe.datasource.model;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentGameStorage {

  private final Map<UUID, CurrentGameEntity> games = new ConcurrentHashMap<>();

  public void save(CurrentGameEntity game) {
    games.put(game.getId(), game);
  }

  public CurrentGameEntity get(UUID id) {
    return games.get(id);
  }
}