package chess.controller;

import chess.domain.ChessGame;
import chess.dto.BoardDto;
import chess.dto.MoveDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public final class WebController {

    private final Gson gson = new Gson();
    private final ChessGame chessGame = new ChessGame();

    public void run() {
        staticFileLocation("/static");

        port(8080);

        get("/", (request, response) -> new HandlebarsTemplateEngine().render(new ModelAndView(null, "index.html")));

        get("/start", (request, response) -> start());

        get("/end", (request, response) -> end());

        get("/status", (request, response) -> gson.toJson(chessGame.status()));

        post("/move", (request, response) -> move(request));

        exception(RuntimeException.class, (exception, request, response) -> exceptionHanding(exception, response));
    }

    private String start() {
        chessGame.start();
        return gson.toJson(new BoardDto(chessGame));
    }

    private boolean end() {
        chessGame.end();
        return true;
    }

    private boolean move(final Request request) {
        final var moveDto = gson.fromJson(request.body(), MoveDto.class);
        chessGame.move(new String[]{moveDto.getFrom(), moveDto.getTo()});
        return chessGame.removedKing();
    }

    private void exceptionHanding(final Exception exception, final Response response) {
        // 이런식으로 처리해도 되는지?
        response.status(500);
        response.body(gson.toJson(exception.getMessage()));
    }
}
