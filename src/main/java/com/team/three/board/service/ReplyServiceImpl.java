package com.team.three.board.service;

import com.team.three.board.domain.Reply;
import com.team.three.board.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    @Override
    public Mono<Reply> saveReply(Reply reply) {
        return this.replyRepository.save(reply);
    }

    //댓글 숨기기/보이기
    public Mono hideReply(Integer replyId) {
        return replyRepository.findById(replyId)
                .filter(Objects::nonNull) //있는 댓번일때
                .filter(item -> {
                    //show상태일때
                    if(item.getUseyn()==1) {
                        item.setUseyn(0);
                    } else {
                        item.setUseyn(1);
                    }
                    return true;
                })
                .flatMap(item -> replyRepository.save(item))
                .log();
    }

    @Override
    public Flux<Reply> getReplies(Integer id) {
        return replyRepository.findByBoardid(id);
    }
}
