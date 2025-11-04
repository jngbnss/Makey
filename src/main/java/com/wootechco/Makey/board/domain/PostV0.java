package com.wootechco.Makey.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시판 도메인

@Entity //jpa에게 이 클래스가 db테이블과 매핑되는 엔티티임을 알려줌
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본 생성자를 만들어줌
// 외부에서 무분별하게 생성하지 못하게 하기 위해서
// jpa는 프록시 생성을 위해 기본 생성자 필수 이기때문에
// 안전장치
@AllArgsConstructor // 도든 필드를 매개변수로 받는 전체 생성자, 빌더랑 같이쓰면 좋음, 주로 테스트나 빌더 패턴용
@Builder // 빌더패턴 자동생성
public class PostV0 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // h2에서는 아이덴티티로 진행
    // auto로 하면 db에 맞춰 자동 선택 됨
    private Long id;

    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    private String author;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate // jpa가 update 쿼리 실행 전에 자동으로 호출해줌
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
