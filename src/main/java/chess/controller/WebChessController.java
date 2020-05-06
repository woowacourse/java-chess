package chess.controller;

import chess.service.ChessBoardService;
import chess.service.ChessGameService;
import chess.service.TurnService;
import chess.service.dto.MovingRequest;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    private ChessBoardService chessBoardService;
    private ChessGameService chessGameService;

    public WebChessController(ChessBoardService chessBoardService, ChessGameService chessGameService) {
        this.chessBoardService = chessBoardService;
        this.chessGameService = chessGameService;
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/onGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return processGame(model);
        });

        get("/initialize", (req, res) -> {
            chessGameService.initialize();
            res.redirect("/onGame");
            return null;
        });

        get("/retrieve", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return processGame(model);
        });

        post("/move", (req, res) -> {
            MovingRequest movingRequest = new MovingRequest(req.queryParams("source"), req.queryParams("destination"));

            chessBoardService.movePiece(movingRequest);
            res.redirect(chessBoardService.getNextPage());
            return null;
        });

        post("/save", (req, res) -> {
            chessGameService.save();
            res.redirect("/");
            return null;
        });

        get("/endGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("notification", chessBoardService.getNotification());
            model.put("chessBoard", chessBoardService.getBoardView());
            model.put("winners", chessBoardService.getWinners());
            return render(model, "endGame.html");
        });
    }

    private Object processGame(Map<String, Object> model) {
        model.put("chessBoard", chessBoardService.getBoardView());
        model.put("notification", chessBoardService.getNotification());
        model.put("teamScore", chessBoardService.getTeamScoreView());
        return render(model, "onGame.html");
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
