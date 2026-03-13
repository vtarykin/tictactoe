package main.java.web.controller;

import main.java.domain.model.CurrentGame;
import main.java.domain.service.GameService;
import main.java.web.mapper.CurrentGameWebMapper;
import main.java.web.model.CurrentGameDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {

  private final GameService gameService;
  private final CurrentGameWebMapper mapper;

  public GameController(GameService gameService) {
    this.gameService = gameService;
    this.mapper = new CurrentGameWebMapper();
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> makeMove(
      @PathVariable UUID id,
      @RequestBody CurrentGameDTO gameDTO
  ) {

    try {

      CurrentGame game = mapper.toDomain(gameDTO);

      if (!id.equals(game.getId())) {
        return ResponseEntity
            .badRequest()
            .body("Game ID mismatch");
      }

      CurrentGame updatedGame = gameService.getNextMove(game);

      CurrentGameDTO response = mapper.toDTO(updatedGame);

      return ResponseEntity.ok(response);

    } catch (Exception e) {

      return ResponseEntity
          .badRequest()
          .body("Invalid game state: " + e.getMessage());
    }
  }
}
