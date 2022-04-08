package chess.controller.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.game.ChessGame;
import chess.service.Service;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
        staticFileLocation("/static");
        Service service = new Service();
        getIndexPage(service);
        createChessGame(service);
        deleteChessGame(service);
        getChessGamePage(service);
        movePiece(service);
        resetChessGame(service);
        getException();
    }

    private void getIndexPage(final Service service) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chess_games", service.loadAllChessGames());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());
    }

    private void createChessGame(final Service service) {
        post("/create_chess_game", (req, res) -> {
            String name = req.queryParams("name");
            service.createChessGame(name);
            res.redirect("/game/" + name);
            return null;
        });
    }

    private void deleteChessGame(final Service service) {
        post("/delete/:name", (req, res) -> {
            String name = req.params(":name");
            service.deleteChessGame(name);
            res.redirect("/");
            return null;
        });
    }

    private void getChessGamePage(final Service service) {
        get("/game/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.params(":name");
            ChessGame chessGame = service.loadChessGame(name);
            model.put("chess_game_name", name);
            model.putAll(chessGame.getCurrentBoardByRawPosition());
            model.put("turn", chessGame.getTurn());
            model.put("result", chessGame.generateResult());
            return new ModelAndView(model, "chess_game.html");
        }, new HandlebarsTemplateEngine());
    }

    private void movePiece(final Service service) {
        post("/move/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            String rawSource = req.queryParams("source").trim().toLowerCase();
            String rawTarget = req.queryParams("target").trim().toLowerCase();
            service.movePiece(chessGameName, rawSource, rawTarget);
            res.redirect("/game/" + chessGameName);
            return null;
        });
    }

    private void resetChessGame(final Service service) {
        post("/reset/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            service.createChessGame(chessGameName);
            res.redirect("/game/" + chessGameName);
            return null;
        });
    }

    private void getException() {
        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
