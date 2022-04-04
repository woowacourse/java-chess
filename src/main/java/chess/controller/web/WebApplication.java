package chess.controller.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame(BoardFactory.generateChessBoard());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.putAll(chessGame.getCurrentBoardForSpark());
            model.put("turn", chessGame.getTurn());
            model.put("result", chessGame.generateResult());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());

        post("/move", (req, res) -> {
            chessGame.move(req.queryParams("source"), req.queryParams("target"));
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body("<a href=\"/\">HOME</a><br/>" + exception.getMessage());
        });
    }
}
