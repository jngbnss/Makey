package com.wootechco.Makey.racinggame.repository;

import com.wootechco.Makey.racinggame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
