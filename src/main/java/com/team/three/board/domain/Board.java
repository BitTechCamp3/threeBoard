package com.team.three.board.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(value = "board")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Board {
    @Id
    private Integer index;
    private String title;

    private String contents;
    private Integer conlike;
    private Integer condislike;
    private String type;

    @CreatedDate
    private LocalDateTime createdt;
    private Integer useyn;
    private String userid;
}
