package com.sparta.chapter3_1.model;

import com.sparta.chapter3_1.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private Long detailId;

    @Column(nullable = false)
    private String contents;

    @Column
    private String nickname;

    public Comment(Long detailId, String contents, String nickname) {
        this.detailId = detailId;
        this.contents = contents;
        this.nickname = nickname;
    }

    public Comment(CommentRequestDto requestDto) {
        this.detailId = requestDto.getDetailId();
        this.contents = requestDto.getContents();
        this.nickname = requestDto.getNickname();
    }
    public void update(CommentRequestDto requestDto) {
        this.detailId = requestDto.getDetailId();
        this.contents = requestDto.getContents();
        this.nickname = requestDto.getNickname();
    }
}
