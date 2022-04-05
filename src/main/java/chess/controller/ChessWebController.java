package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
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
        get("/result", this::result);
    }

    private String start(Request request, Response response) {
        chessGame.start();

        Map<String, Object> model = new HashMap<>();
        Map<Color, Double> scores = chessGame.calculateScore();

        model.put("board", new BoardDto(chessGame.getBoard().getValue()));
        model.put("black", scores.get(Color.BLACK));
        model.put("white", scores.get(Color.WHITE));

        return render(model, "index.html");
    }

    private String move(Request request, Response response) {
        String source = request.queryMap().get("source").value();
        String target = request.queryMap().get("target").value();

        chessGame.movePiece(source, target);

        if (chessGame.isFinish()) {
            response.redirect("/result");
            return null;
        }
        response.redirect("/update");
        return null;
    }

    private String update(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        Map<Color, Double> scores = chessGame.calculateScore();

        model.put("board", new BoardDto(chessGame.getBoard().getValue()));
        model.put("black", scores.get(Color.BLACK));
        model.put("white", scores.get(Color.WHITE));

        return render(model, "index.html");
    }

    private String result(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("winner", chessGame.judgeWinner());
        return render(model, "result.html");
    }

    private String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
