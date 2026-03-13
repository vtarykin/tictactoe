package main.java.datasource.repository;

import java.util.UUID;
import main.java.datasource.model.CurrentGameEntity;

public class InMemoryGameRepository implements GameRepository {

  private final GameStorage storage;

  public InMemoryGameRepository(GameStorage storage) {
    this.storage = storage;
  }

  @Override
  public void save(CurrentGameEntity game) {
    storage.save(game);
  }

  @Override
  public CurrentGameEntity getCurrentGame(UUID id) {
    return storage.findById(id);
  }
}
