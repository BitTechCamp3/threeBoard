package com.team.three.board.handler;

import com.team.three.board.domain.Board;
import com.team.three.board.domain.Reply;
import com.team.three.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class ReplyHandler {

    private final ReplyService replyService;
    public Mono<ServerResponse> writeReply(ServerRequest request) {
        Mono<Reply> replyMono = request.bodyToMono(Reply.class)
                .flatMap(reply -> replyService.saveReply(reply))
                .log("replyMono is : ");

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(replyMono, Reply.class).log("writeReply is : ");
    }

    // 댓글 숨김
    public Mono<ServerResponse> hideReply(ServerRequest req) {
        Mono<Reply> mono = req.bodyToMono(Reply.class)
                .flatMap(reply -> replyService.hideReply(reply.getIndex()))
                .log();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, Reply.class).log("reply hided : ");
    }


    public Mono<ServerResponse> getReplies(ServerRequest request) {
        Integer boardId = Integer.valueOf(request.pathVariable("id"));

        Flux<Reply> list = replyService.getReplies(boardId);


        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(list, Reply.class).log();
    }
}
