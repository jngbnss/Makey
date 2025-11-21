package com.wootechco.Makey.racinggame.repository;

import com.wootechco.Makey.racinggame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
