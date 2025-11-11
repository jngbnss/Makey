package com.wootechco.Makey.racinggame.model;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int position;
    private int lastDice; // ✅ 프론트 화면에서 dice 표시용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    public Player() {}

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
        this.position = 0;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPosition() { return position; }
    public int getLastDice() { return lastDice; }

    public void setLastDice(int d) { this.lastDice = d; }

    public void move() { this.position++; }
}
