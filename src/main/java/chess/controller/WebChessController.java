package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.converter.Converter;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.game.StatusCalculator;
import chess.service.ChessService;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final ChessService chessService = new ChessService(new BoardDao(), new GameDao());

    public void run() {
        port(8081);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            return new ModelAndView(null, "start.html");
        }, new HandlebarsTemplateEngine());

        get("/game/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));
            Board board = chessService.findBoardByGameId(gameId);

            Map<String, Object> map = Converter.toMap(gameId, board);
            return new ModelAndView(map, "index.html");
        }, new HandlebarsTemplateEngine());

        post("/game/:id/move", (req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");

            int gameId = Integer.parseInt(req.params(":id"));
            chessService.move(gameId, from, to);

            res.redirect("/game/" + gameId);
            return null;
        });

        post("/start", (req, res) -> {
            String whiteUserName = req.queryParams("white_user_name");
            String blackUserName = req.queryParams("black_user_name");

            chessService.start(whiteUserName, blackUserName);
            int gameId = chessService.findGameIdByUserName(whiteUserName, blackUserName);

            res.redirect("/game/" + gameId);
            return null;
        });

        post("/stop", (req, res) -> {
            res.redirect("/");
            return null;
        });

        post("/game/:id/end", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));
            chessService.endGame(gameId);
            res.redirect("/");
            return null;
        });

        get("/game/:id/status", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));

            StatusCalculator status = chessService.createStatus(gameId);
            Board board = chessService.findBoardByGameId(gameId);

            Map<String, Object> map = Converter.toMap(gameId, board, status);
            return new ModelAndView(map, "index.html");
        }, new HandlebarsTemplateEngine());

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(exception.getMessage());
        });
    }
}
