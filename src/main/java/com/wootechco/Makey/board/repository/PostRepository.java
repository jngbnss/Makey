package com.wootechco.Makey.board.repository;

import com.wootechco.Makey.board.domain.PostV0;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostV0,Long> {
}
