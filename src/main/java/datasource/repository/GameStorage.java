package main.java.datasource.repository;

import main.java.datasource.model.CurrentGameEntity;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GameStorage {

  private final ConcurrentMap<UUID, CurrentGameEntity> storage = new ConcurrentHashMap<>();

  public void save(CurrentGameEntity game) {
    storage.put(game.getId(), game);
  }

  public CurrentGameEntity findById(UUID id) {
    return storage.get(id);
  }

}