package com.wootechco.Makey.racinggame.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int position;

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
    public void move() { this.position++; }
}

