package com.wootechco.Makey.board.controller;

import com.wootechco.Makey.board.dto.PostRequestDto;
import com.wootechco.Makey.board.dto.PostResponseDto;
import com.wootechco.Makey.board.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
//@CrossOrigin(origins = "http://localhost:5173") // React 개발 서버 주소
public class PostController {
    private final PostService postService;

    @PostMapping
    public Long create(@RequestBody PostRequestDto dto) {
        return postService.create(dto);
    }

    @GetMapping
    public List<PostResponseDto> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PostRequestDto dto) {
        postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }

}
