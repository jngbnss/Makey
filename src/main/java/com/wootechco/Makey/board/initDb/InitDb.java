package com.wootechco.Makey.board.initDb;

import com.wootechco.Makey.board.service.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.wootechco.Makey.board.dto.PostRequestDto;


@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final PostService postService;

        public void dbInit() {
            postService.create(new PostRequestDto(
                    "아침 커피 한 잔",
                    "오늘도 커피 한 잔으로 시작합니다.",
                    "카페러버"
            ));

            postService.create(new PostRequestDto(
                    "점심 메뉴 추천",
                    "간단하지만 든든한 샌드위치 어때요?",
                    "푸드러버"
            ));

            postService.create(new PostRequestDto(
                    "퇴근 후 산책",
                    "저녁 하늘이 예쁘네요. 잠깐 산책 다녀오세요.",
                    "걷기좋은날"
            ));
        }
    }
}