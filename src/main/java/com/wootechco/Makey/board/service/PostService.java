package com.wootechco.Makey.board.service;

import com.wootechco.Makey.board.domain.PostRequestDto;
import com.wootechco.Makey.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long create(PostRequestDto dto){
        dto.toEntity();
    }


}
