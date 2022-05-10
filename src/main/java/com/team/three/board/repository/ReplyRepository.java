package com.team.three.board.repository;

import com.team.three.board.domain.Reply;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

public interface ReplyRepository extends ReactiveCrudRepository<Reply, Integer> {

    Flux<Reply> findByBoardid(Integer boardid);

    //and boardId = $1
}
