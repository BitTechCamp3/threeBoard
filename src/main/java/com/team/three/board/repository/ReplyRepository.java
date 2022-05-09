package com.team.three.board.repository;

import com.team.three.board.domain.Reply;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReplyRepository extends ReactiveCrudRepository<Reply, Integer> {
}
