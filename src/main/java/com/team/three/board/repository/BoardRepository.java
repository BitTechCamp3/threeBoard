package com.team.three.board.repository;

import com.team.three.board.domain.Board;
import com.team.three.board.domain.Count;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoardRepository extends ReactiveSortingRepository<Board, Integer> {
    @Query("SELECT `index`, title, conlike, condislike, `type`, createdt, userid\n" +
            "FROM board WHERE useyn = 1 AND `type` = 1 ORDER BY `index` DESC")
    Flux<Board> findAllByCommunityPage(Pageable pageable);

    @Query("SELECT `index`, title, conlike, condislike, `type`, createdt, useyn, userid\n" +
            "FROM board WHERE useyn = 1 AND `type` = 2 ORDER BY `index` DESC")
    Flux<Board> findAllByQnaPage(Pageable pageable);

    @Query("SELECT COUNT(*) AS count FROM board WHERE type = 1 AND useyn = 1")
    Mono<Count> countAllByTypeAndUseyn();
}
