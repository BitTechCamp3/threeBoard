package com.team.three.board.router;

import com.team.three.board.handler.ReplyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ReplyRouter {
    @Bean
    public RouterFunction<ServerResponse> route2(ReplyHandler handler) {
        return RouterFunctions
                .route(POST("/board/addReply").and(accept(MediaType.APPLICATION_JSON)), handler::writeReply)  // 댓글 작성
                .andRoute(POST("/reply/hide").and(accept(MediaType.APPLICATION_JSON)), handler::hideReply) // 댓글 숨김, 보임
                .andRoute(GET("/reply/id/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getReplies)
                ;
    }

}
