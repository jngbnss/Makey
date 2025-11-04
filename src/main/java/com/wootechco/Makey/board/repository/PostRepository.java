package com.wootechco.Makey.board.repository;

import com.wootechco.Makey.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
