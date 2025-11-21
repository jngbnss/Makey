package com.wootechco.Makey.racinggame.controller;
import com.wootechco.Makey.racinggame.model.Game;
import com.wootechco.Makey.racinggame.service.GameService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public Game startGame(@RequestBody StartRequest req) {
        return gameService.startGame(req.rounds, req.playerNames);
    }

    @PostMapping("/{id}/round")
    public Game playRound(@PathVariable Long id) {
        return gameService.playRound(id);
    }

    public static class StartRequest {
        public int rounds;
        public List<String> playerNames;
    }
}
