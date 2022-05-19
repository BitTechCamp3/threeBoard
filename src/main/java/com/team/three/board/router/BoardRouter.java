package com.team.three.board.router;

import com.team.three.board.handler.BoardHandler;
import com.team.three.board.handler.ReplyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BoardRouter {
    @Bean
    public RouterFunction<ServerResponse> route(BoardHandler handler) {
        return RouterFunctions
                .route(POST("/boards/community/write").and(accept(MediaType.APPLICATION_JSON)), handler::writeBoard)
                .andRoute(POST("/boards/qna/write").and(accept(MediaType.APPLICATION_JSON)), handler::writeBoard)
                .andRoute(POST("/board/hide").and(accept(MediaType.APPLICATION_JSON)), handler::hideArticle)
                .andRoute(POST("/board/addLike").and(accept(MediaType.APPLICATION_JSON)), handler::updateLike)
                .andRoute(POST("/board/addDisLike").and(accept(MediaType.APPLICATION_JSON)), handler::updateDisLike)
                .andRoute(GET("/boards/community").and(accept(MediaType.APPLICATION_JSON)), handler::getBoardCommunityList)
                .andRoute(GET("/boards/qna").and(accept(MediaType.APPLICATION_JSON)), handler::getBoardQnAList)
                .andRoute(GET("/board/id/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getArticle)
                .andRoute(GET("/board/count/community").and(accept(MediaType.APPLICATION_JSON)), handler::getCount)
                ;
    }
}
