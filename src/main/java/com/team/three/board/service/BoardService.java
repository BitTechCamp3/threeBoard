package com.team.three.board.service;

import com.team.three.board.domain.Board;
import com.team.three.board.domain.Count;
import org.springframework.data.relational.core.sql.In;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public interface BoardService {

    Mono<Board> saveBoard(Board board);
    //게시글 숨김
    Mono<Board> hideBoard(Integer articleId);
    Mono<Board> changeLike(Board board);
    Mono<Board> changeDisLike(Board board);
    Mono getArticle(Integer boardid);
    Mono<Count> getCount();
}
