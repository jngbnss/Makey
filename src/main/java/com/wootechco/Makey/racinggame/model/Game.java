package com.wootechco.Makey.racinggame.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rounds;
    private int currentRound = 0;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public Game() { }

    public Game(int rounds) {
        this.rounds = rounds;
    }

    public Long getId() { return id; }
    public int getRounds() { return rounds; }
    public int getCurrentRound() { return currentRound; }
    public List<Player> getPlayers() { return players; }

    public void addPlayers(List<Player> list) {
        this.players.addAll(list);
    }

    public void nextRound() {
        this.currentRound++;
    }

    public boolean isFinished() {
        return currentRound >= rounds;
    }
}
