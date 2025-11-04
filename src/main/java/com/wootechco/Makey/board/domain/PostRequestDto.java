package com.wootechco.Makey.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter
@NoArgsConstructor//파라미터가 없는 기본 생성자
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private String title;
    private String content;
    private String author;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    // 새로 알게 된 내용
    /**
     * 빌더 패턴이란?
     * 복잡한 객체를 단계별로 만들어주는 설계 패턴
     * 여러 필드가 있는 객체를 생성할 때 생성자에 모든 값을 한번에 넣지 않고,
     * 조금씩 세팅하면서 객체를 만들어주는 방식
     */


}
