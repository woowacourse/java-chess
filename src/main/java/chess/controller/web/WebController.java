package chess.controller.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.game.ChessGame;
import chess.dto.ChessGameExceptBoardDto;
import chess.service.ChessGameService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
        staticFileLocation("/static");
        ChessGameService chessGameService = new ChessGameService();
        getIndexPage(chessGameService);
        createChessGame(chessGameService);
        deleteChessGame(chessGameService);
        getChessGamePage(chessGameService);
        movePiece(chessGameService);
        resetChessGame(chessGameService);
        getException();
    }

    private void getIndexPage(final ChessGameService chessGameService) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chess_games", chessGameService.loadAllChessGames());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());
    }

    private void createChessGame(final ChessGameService chessGameService) {
        post("/create_chess_game", (req, res) -> {
            String name = req.queryParams("name");
            chessGameService.createChessGame(name);
            res.redirect("/game/" + name);
            return null;
        });
    }

    private void deleteChessGame(final ChessGameService chessGameService) {
        post("/delete/:name", (req, res) -> {
            String name = req.params(":name");
            chessGameService.deleteChessGame(name);
            res.redirect("/");
            return null;
        });
    }

    private void getChessGamePage(final ChessGameService chessGameService) {
        get("/game/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.params(":name");
            ChessGame chessGame = chessGameService.loadChessGame(name);
            model.put("chess_game_name", name);
            model.putAll(chessGame.getCurrentBoardByRawPosition());
            model.put("turn", chessGame.getTurn());
            model.put("result", chessGame.generateResult());
            return new ModelAndView(model, "chess_game.html");
        }, new HandlebarsTemplateEngine());
    }

    private void movePiece(final ChessGameService chessGameService) {
        post("/move/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            String rawSource = req.queryParams("source").trim().toLowerCase();
            String rawTarget = req.queryParams("target").trim().toLowerCase();
            chessGameService.movePiece(chessGameName, rawSource, rawTarget);
            res.redirect("/game/" + chessGameName);
            return null;
        });
    }

    private void resetChessGame(final ChessGameService chessGameService) {
        post("/reset/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            chessGameService.createChessGame(chessGameName);
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
