package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGame;
import chess.domain.board.Location;
import chess.domain.state.State;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        ChessGame chessGame = new ChessGame();
        WebChessService webChessService = new WebChessService();

        get("/", (req, res) -> {
            State state = webChessService.getState();
            if (state.isRunning()) {
                chessGame.setState(state);
            }
            return new ModelAndView(chessGame.toMap(), "index.html");
        }, new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            chessGame.start();
            webChessService.initializeGame(chessGame.getState());
            res.redirect("/");
            return null;
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            chessGame.move(Location.of(source), Location.of(target));

            webChessService.move(source, target);
            webChessService.updateState(chessGame.getState());
            res.redirect("/");
            return null;
        });

        get("/status", (req, res) -> {
            return chessGame.status();
        }, new JsonTransformer());

        get("/end", (req, res) -> {
            chessGame.end();
            webChessService.updateState(chessGame.getState());
            res.redirect("/");
            return null;
        });
    }
}
