package com.wootechco.Makey.racinggame.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rounds;
    private int currentRound;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Player> players;

    public Game() {}
    public Game(int rounds) {
        this.rounds = rounds;
        this.currentRound = 0;
    }

    public Long getId() { return id; }
    public int getRounds() { return rounds; }
    public int getCurrentRound() { return currentRound; }
    public void nextRound() { this.currentRound++; }
    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public boolean isFinished() { return currentRound >= rounds; }
}
