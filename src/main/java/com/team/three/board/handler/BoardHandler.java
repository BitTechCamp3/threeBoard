package com.team.three.board.handler;

import com.team.three.board.domain.Board;
import com.team.three.board.domain.Count;
import com.team.three.board.domain.Reply;
import com.team.three.board.repository.BoardRepository;
import com.team.three.board.service.BoardService;
import com.team.three.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
@Slf4j
public class BoardHandler {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    public Mono<ServerResponse> getBoardCommunityList(ServerRequest request) {
        Integer page =  request.queryParam("page").isPresent() ? Integer.parseInt(request.queryParam("page").get()) - 1 : 0;
        Integer pageSize = request.queryParam("page-size").isPresent() ? Integer.parseInt(request.queryParam("page-size").get()) : 10;
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return Mono.just(boardRepository.findAllByCommunityPage(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdt"))))
                .flatMap(board -> ServerResponse.ok().contentType(APPLICATION_JSON).body(board, Board.class).switchIfEmpty(notFound));
    }

    public Mono<ServerResponse> getBoardQnAList(ServerRequest request) {
        Integer page =  request.queryParam("page").isPresent() ? Integer.parseInt(request.queryParam("page").get()) - 1 : 0;
        Integer pageSize = request.queryParam("page-size").isPresent() ? Integer.parseInt(request.queryParam("page-size").get()) : 10;
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return Mono.just(boardRepository.findAllByQnaPage(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdt"))))
                .flatMap(board -> ServerResponse.ok().contentType(APPLICATION_JSON).body(board, Board.class).switchIfEmpty(notFound));
    }

    public Mono<ServerResponse> getArticle(ServerRequest request) {
        Integer boardId = Integer.valueOf(request.pathVariable("id"));

        Mono<HashMap> map = boardService.getArticle(boardId)
                .log("got Article");

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(map, Board.class).log();
    }

    public Mono<ServerResponse> getCount(ServerRequest req) {
        Mono<Count> mono = req.bodyToMono(Board.class)
                .flatMap(arti -> boardService.getCount()).log();

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(mono, Count.class).log("countBoard is : ");
    }

    public Mono<ServerResponse> writeBoard(ServerRequest request) {
        Mono<Board> boardMono = request.bodyToMono(Board.class)
                .flatMap(reply -> boardService.saveBoard(reply))
                .log("boardMono is : ");

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(boardMono, Board.class).log("writeBoard is : ");
    }

    public Mono<ServerResponse> hideArticle(ServerRequest req) {
        Mono<Board> mono = req.bodyToMono(Board.class)
                .flatMap(arti -> boardService.hideBoard(arti.getIndex())).log();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, Board.class).log("writeBoard is : ");
    }

    public Mono<ServerResponse> updateLike(ServerRequest req) {
        Mono<Board> mono = req.bodyToMono(Board.class)
                .flatMap(br -> boardService.changeLike(br)).log();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, Board.class).log("update Like");
    }

    public Mono<ServerResponse> updateDisLike(ServerRequest req) {
        Mono<Board> mono = req.bodyToMono(Board.class)
                .flatMap(br -> boardService.changeDisLike(br)).log();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, Board.class).log("update DisLike");
    }

}
