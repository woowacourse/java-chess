package chess.controller;

import static spark.Spark.*;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    public void run() {
        staticFileLocation("/static");

        get("/", this::start);
    }

    private String start(Request request, Response response) {
        final var board = new Board(BoardFactory.getInitialPieces());
        return render(new BoardDto(board.getValue()), "index.html");
    }

    private String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
