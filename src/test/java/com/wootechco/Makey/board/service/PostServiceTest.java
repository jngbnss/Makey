package com.wootechco.Makey.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.wootechco.Makey.board.dto.PostRequestDto;
import com.wootechco.Makey.board.dto.PostResponseDto;
import com.wootechco.Makey.board.repository.PostRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(PostService.class) // Service를 테스트하려면 import 필요
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository.deleteAll(); // 테스트마다 초기화
    }

    @Test
    void createTest() {
        PostRequestDto dto = new PostRequestDto("제목1", "내용1", "작성자1");
        Long id = postService.create(dto);

        PostResponseDto response = postService.findById(id);
        assertThat(response.getTitle()).isEqualTo("제목1");
        assertThat(response.getContent()).isEqualTo("내용1");
    }

    @Test
    void findAllTest() {
        postService.create(new PostRequestDto("제목1", "내용1", "작성자1"));
        postService.create(new PostRequestDto("제목2", "내용2", "작성자2"));

        List<PostResponseDto> posts = postService.findAll();
        assertThat(posts).hasSize(2);
    }

    @Test
    void updateTest() {
        Long id = postService.create(new PostRequestDto("제목1", "내용1", "작성자1"));

        postService.update(id, new PostRequestDto("수정된 제목", "수정된 내용", "작성자1"));
        PostResponseDto updated = postService.findById(id);
        assertThat(updated.getTitle()).isEqualTo("수정된 제목");
        assertThat(updated.getContent()).isEqualTo("수정된 내용");
    }

    @Test
    void deleteTest() {
        Long id = postService.create(new PostRequestDto("제목1", "내용1", "작성자1"));
        postService.delete(id);

        assertThrows(IllegalArgumentException.class, () -> postService.findById(id));
    }
}