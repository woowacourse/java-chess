package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.ScoreCalculator;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private ChessGame chessGame;

    public void run() {
        port(9090);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lobby.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame = new ChessGame();
            return render(model, "game.html");
        });

        get("/start", (req, res) -> {
           Map<String, Object> model = new HashMap<>();
           chessGame.start();
           model.put("pieces", chessGame.getPieces());
           return render(model, "game.html");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.end();
            ScoreCalculator scoreCalculator = new ScoreCalculator(chessGame.getValue());
            Map<Team, Double> status = scoreCalculator.createStatus();
            model.put("blackScore", status.get(Team.BLACK));
            model.put("whiteScore", status.get(Team.WHITE));
            model.put("winTeam", chessGame.getWinTeam(status));
            return render(model, "game.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ScoreCalculator scoreCalculator = new ScoreCalculator(chessGame.getValue());
            Map<Team, Double> status = scoreCalculator.createStatus();
            model.put("blackScore", status.get(Team.BLACK));
            model.put("whiteScore", status.get(Team.WHITE));
            model.put("pieces", chessGame.getPieces());
            return render(model, "game.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.move(req.queryParams("from"), req.queryParams("to"));
            model.put("pieces", chessGame.getPieces());
            return render(model, "game.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
