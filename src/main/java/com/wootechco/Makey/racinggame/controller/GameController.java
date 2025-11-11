package com.wootechco.Makey.racinggame.controller;


import com.wootechco.Makey.racinggame.model.Game;
import com.wootechco.Makey.racinggame.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
//@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public Game startGame(@RequestBody StartRequest request) {
        return gameService.startGame(request.rounds, request.playerNames);
    }

    @PostMapping("/{gameId}/round")
    public Game playRound(@PathVariable Long gameId) {
        return gameService.playRound(gameId);
    }

    public static class StartRequest {
        public int rounds;
        public List<String> playerNames;
    }
}
