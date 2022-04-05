package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Positions;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebController {

    public void run() {
        staticFileLocation("/static");
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            Map<String, Piece> model = new LinkedHashMap<>();
            return modelAndView(model, "game.html");
        }, new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            if (!chessGame.isRunning()) {
                chessGame.start();
            }
            Map<String, Piece> board = chessGame.getChessBoard().toModel();
            return modelAndView(board, "game.html");
        }, new HandlebarsTemplateEngine());

        post("/move", (req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");
            chessGame.move(Positions.findPosition(from), Positions.findPosition(to));

            if (chessGame.isFinished()) {
                Map<String, Object> model = new HashMap<>();
                model.put("white", chessGame.computeScore(Color.WHITE));
                model.put("black", chessGame.computeScore(Color.BLACK));
                return modelAndView(model, "game.html");
            }
            res.redirect("/start");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>(chessGame.getChessBoard().toModel());
            model.put("white", chessGame.computeScore(Color.WHITE));
            model.put("black", chessGame.computeScore(Color.BLACK));
            return modelAndView(model, "game.html");
        }, new HandlebarsTemplateEngine());

        get("/end", (req, res) -> {
            chessGame.end();
            return modelAndView(chessGame.getChessBoard().toModel(), "game.html");
        }, new HandlebarsTemplateEngine());

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage() + "<br> 뒤로 가기를 눌러주세요. ");
        });
    }
}
