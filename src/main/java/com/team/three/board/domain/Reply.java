package com.team.three.board.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "reply")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reply {
    private Integer index;

    private Integer boardid; //게시글 아이디

    private Integer userid; //작성자

    private String replycontent; //댓글 내용

    private Integer useyn; //댓글 노출 여부

    @CreatedDate
    private LocalDateTime createdt; //생성일

}
