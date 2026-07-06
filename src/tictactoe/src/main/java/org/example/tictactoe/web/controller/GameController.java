package org.example.tictactoe.web.controller;

import java.util.UUID;
import org.example.tictactoe.datasource.repository.CurrentGameRepository;
import org.example.tictactoe.domain.model.CurrentGame;
import org.example.tictactoe.domain.service.GameService;
import org.example.tictactoe.web.mapper.CurrentGameWebMapper;
import org.example.tictactoe.web.model.CurrentGameDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*")
public class GameController {

  private final GameService gameService;
  private final CurrentGameRepository repository;
  private final CurrentGameWebMapper mapper;

  public GameController(GameService gameService,
                        CurrentGameRepository repository,
                        CurrentGameWebMapper mapper) {
    this.gameService = gameService;
    this.repository = repository;
    this.mapper = mapper;
  }
  @PostMapping("/new")
  public ResponseEntity<CurrentGameDTO> newGame() {
    CurrentGame game = new CurrentGame();
    repository.save(game);

    return ResponseEntity.ok(mapper.toDTO(game));
  }
  @PostMapping("/{id}")
  public ResponseEntity<?> playGame(@PathVariable UUID id, @RequestBody CurrentGameDTO dto) {
    try {
      CurrentGame existingGame = repository.get(id);

      if (existingGame == null) {
        return ResponseEntity.badRequest().body("Game not found!");
      }

      CurrentGame incomingGame = mapper.toDomain(dto);

      if (!gameService.validateMove(existingGame, incomingGame)) {
        return ResponseEntity.badRequest().body("Invalid move!");
      }


      existingGame.getBoard().setBoard(incomingGame.getBoard().getBoard());

      CurrentGame updated = gameService.nextMove(existingGame);
      if (gameService.isGameOver(updated)) {
        String result = gameService.getGameResult(updated);
        if (!result.equals("In progress")) {
          return ResponseEntity.ok(result );
        }
      }
      return ResponseEntity.ok(mapper.toDTO(updated));

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}