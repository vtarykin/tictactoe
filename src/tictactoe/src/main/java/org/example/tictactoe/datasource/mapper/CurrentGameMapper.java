package org.example.tictactoe.datasource.mapper;

import org.example.tictactoe.domain.model.CurrentGame;
import org.example.tictactoe.datasource.model.CurrentGameStorage;

public class CurrentGameMapper {

  public CurrentGame toDomain(CurrentGame game) {
    return game;
  }

  public CurrentGame toDatasource(CurrentGame game) {
    return game;
  }
}