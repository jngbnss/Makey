package com.wootechco.Makey.board.service;

import com.wootechco.Makey.board.domain.Post;
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
    public Long create(PostRequestDto dto) {
        Post post = dto.toEntity();
        postRepository.save(post);
        return post.getId();
    }

    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::from)
                .toList();
    }

    public PostResponseDto findById(Long id) {
        return postRepository.findById(id)
                .map(PostResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    @Transactional
    public void update(Long id, PostRequestDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.update(dto.getTitle(), dto.getContent());
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
