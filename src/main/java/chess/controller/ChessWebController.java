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
        post("/move", this::move);
        get("/update", this::update);
        get("/result", this::result);
        get("/restart", this::restart);
    }

    private String start(Request request, Response response) {
        chessService.startGame();

        Map<String, Object> model = new HashMap<>();
        Map<Color, Double> scores = chessService.getGameScores();

        model.put("board", chessService.getBoard());
        model.put("black", scores.get(Color.BLACK));
        model.put("white", scores.get(Color.WHITE));

        return render(model, "index.html");
    }

    private String move(Request request, Response response) {
        String source = request.queryMap().get("source").value();
        String target = request.queryMap().get("target").value();

        chessService.movePiece(source, target);

        if (chessService.isGameFinish()) {
            response.redirect("/result");
            return null;
        }
        response.redirect("/update");
        return null;
    }

    private String update(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        Map<Color, Double> scores = chessService.getGameScores();

        model.put("board", chessService.getBoard());
        model.put("black", scores.get(Color.BLACK));
        model.put("white", scores.get(Color.WHITE));

        return render(model, "index.html");
    }

    private String result(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("winner", chessService.getWinner());
        return render(model, "result.html");
    }

    private String restart(Request request, Response response) {
        chessService.deleteCurrentGame();
        response.redirect("/");
        return null;
    }

    private String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
