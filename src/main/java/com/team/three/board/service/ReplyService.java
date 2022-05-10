package com.team.three.board.service;

import com.team.three.board.domain.Reply;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReplyService {
    Mono<Reply> saveReply(Reply reply);

    //댓글 숨김
    Mono<Reply> hideReply(Integer id);

    Flux<Reply> getReplies(Integer boardid);
}
