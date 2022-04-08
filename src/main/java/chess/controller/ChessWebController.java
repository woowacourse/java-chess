package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private ChessService chessService = new ChessService();

    public void run() {
        staticFileLocation("/static");

        get("/", this::start);
        post("/restart", this::restart);
        post("/move", this::move);
        get("/result", this::result);
        exception(Exception.class, this::handleException);
    }

    private String start(Request request, Response response) {
        if (chessService.isGameWaiting() || chessService.isGameFinish()) {
            chessService.startInitializedGame();
        }

        final Map<String, Object> model = new HashMap<>();
        final Map<Color, Double> scores = chessService.getGameScores();

        model.put("board", chessService.getBoard());
        model.put("black", scores.get(Color.BLACK));
        model.put("white", scores.get(Color.WHITE));

        return render(model, "game.html");
    }

    private String restart(Request request, Response response) {
        chessService.startInitializedGame();
        response.redirect("/");
        return null;
    }

    private String move(Request request, Response response) {
        final String source = request.queryMap().get("source").value();
        final String target = request.queryMap().get("target").value();

        chessService.movePiece(source, target);

        if (chessService.isGameFinish()) {
            response.redirect("/result");
            return null;
        }
        response.redirect("/");
        return null;
    }

    private String result(Request request, Response response) {
        final Map<String, Object> model = new HashMap<>();
        model.put("winner", chessService.getWinner());
        return render(model, "result.html");
    }

    private void handleException(Exception exception, Request request, Response response) {
        response.status(400);
        response.body(exception.getMessage());
    }

    private String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
