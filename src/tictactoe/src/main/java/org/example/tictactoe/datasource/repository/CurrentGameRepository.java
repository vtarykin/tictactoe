package org.example.tictactoe.datasource.repository;

import org.example.tictactoe.datasource.mapper.CurrentGameMapper;
import org.example.tictactoe.datasource.model.CurrentGameEntity;
import org.example.tictactoe.datasource.model.CurrentGameStorage;
import org.example.tictactoe.domain.model.CurrentGame;

import java.util.UUID;

public class CurrentGameRepository {

  private final CurrentGameStorage storage;
  private final CurrentGameMapper mapper;

  public CurrentGameRepository(CurrentGameStorage storage) {
    this.storage = storage;
    this.mapper = new CurrentGameMapper();
  }

  public void save(CurrentGame game) {
    CurrentGameEntity entity = mapper.toEntity(game);
    storage.save(entity);
  }

  public CurrentGame get(UUID id) {
    CurrentGameEntity entity = storage.get(id);
    return entity == null ? null : mapper.toDomain(entity);
  }
}