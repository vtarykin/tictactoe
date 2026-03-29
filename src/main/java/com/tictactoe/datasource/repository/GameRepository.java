package main.java.datasource.repository;

import main.java.datasource.model.CurrentGameEntity;

import java.util.UUID;

public interface GameRepository {

  void save(CurrentGameEntity game);

  CurrentGameEntity getCurrentGame(UUID id);

}