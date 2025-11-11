package com.wootechco.Makey.racinggame.service;

import com.wootechco.Makey.racinggame.model.Game;
import com.wootechco.Makey.racinggame.model.Player;
import com.wootechco.Makey.racinggame.repository.GameRepository;
import com.wootechco.Makey.racinggame.repository.PlayerRepository;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final Random random = new Random();

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }
    @Transactional
    public Game startGame(int rounds, List<String> playerNames) {
        Game game = new Game(rounds);

        // 먼저 game 저장
        Game savedGame = gameRepository.save(game);

        // Player 생성 및 game과 연결
        List<Player> players = playerNames.stream()
                .map(name -> new Player(name, savedGame))
                .collect(Collectors.toList()); // Java 8 기준

        // player 저장
        playerRepository.saveAll(players);

        // game 엔티티에 players 설정
        savedGame.getPlayers().addAll(players);

        return savedGame;
    }


    @Transactional
    public Game playRound(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임이 존재하지 않습니다"));

        if (game.isFinished()) return game;

        for (Player player : game.getPlayers()) {
            int dice = random.nextInt(6) + 1;
            if (dice >= 3) {
                player.move();
                playerRepository.save(player);
            }
        }

        game.nextRound();
        gameRepository.save(game);

        return game;
    }
}
