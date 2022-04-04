package chess.controller;

import static spark.Spark.*;

import chess.domain.game.ChessGame;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private ChessGame chessGame = new ChessGame();

    public void run() {
        staticFileLocation("/static");

        get("/", this::start);
        post("/move", this::move);
        get("/update", this::update);
    }

    private String start(Request request, Response response) {
        chessGame.start();
        return render(new BoardDto(chessGame.getBoard().getValue()), "index.html");
    }

    private String move(Request request, Response response) {
        String source = request.queryMap().get("source").value();
        String target = request.queryMap().get("target").value();

        chessGame.movePiece(source, target);

        response.redirect("/update");
        return null;
    }

    private String update(Request request, Response response) {
        return render(new BoardDto(chessGame.getBoard().getValue()), "index.html");
    }

    private String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
