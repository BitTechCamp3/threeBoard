package com.team.three.board.service;

import com.team.three.board.domain.Article;
import com.team.three.board.domain.Board;
import com.team.three.board.domain.Reply;
import com.team.three.board.repository.BoardRepository;
import com.team.three.board.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyRepository replyRepository;

    @Override
    public Mono<Board> saveBoard(Board board) {
        return this.boardRepository.save(board);
    }

    @Override
    public Mono<Board> hideBoard(Integer articleId) {
        return boardRepository.findById(articleId)
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
                .flatMap(item -> boardRepository.save(item))
                .log();
    }

    @Override
    public Mono<Board> changeLike(Board board) {
        return boardRepository.findById(board.getIndex())
                .filter(Objects::nonNull)
                .doOnNext(item -> item.setConlike(item.getConlike()+1))
                .flatMap(item -> boardRepository.save(item))
                .log();
    }

    @Override
    public Mono<Board> changeDisLike(Board board) {
        return boardRepository.findById(board.getIndex())
                .filter(Objects::nonNull)
                .doOnNext(item -> item.setCondislike(item.getCondislike()+1))
                .flatMap(item -> boardRepository.save(item))
                .log();
    }

    @Override
    public Mono<Board> getArticle(Integer boardid) {
        Mono<Board> bd = boardRepository.findById(boardid);
        return bd;

    }
}
