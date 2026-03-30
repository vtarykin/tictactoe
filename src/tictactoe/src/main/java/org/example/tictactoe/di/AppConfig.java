package org.example.tictactoe.di;

import org.example.tictactoe.datasource.model.CurrentGameStorage;
import org.example.tictactoe.datasource.repository.CurrentGameRepository;
import org.example.tictactoe.datasource.service.GameServiceImpl;
import org.example.tictactoe.domain.service.GameService;
import org.example.tictactoe.web.mapper.CurrentGameWebMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public CurrentGameStorage storage() {
    return new CurrentGameStorage();
  }

  @Bean
  public CurrentGameRepository repository(CurrentGameStorage storage) {
    return new CurrentGameRepository(storage);
  }

  @Bean
  public GameService gameService(CurrentGameRepository repository) {
    return new GameServiceImpl(repository);
  }

  @Bean
  public CurrentGameWebMapper webMapper() {
    return new CurrentGameWebMapper();
  }
}