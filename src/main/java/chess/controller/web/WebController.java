package chess.controller.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
        staticFileLocation("/static");
        getIndexPage();
        createChessGame();
        deleteChessGame();
        getGamePage();
        movePiece();
        resetChessGame();
        getException();
    }

    private void getIndexPage() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chess_games", ChessGameDao.loadAll());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());
    }

    private void createChessGame() {
        post("/create_chess_game", (req, res) -> {
            String name = req.queryParams("name");
            ChessGameDao.save(ChessGame.createInit(name));
            res.redirect("/game/" + name);
            return null;
        });
    }

    private void deleteChessGame() {
        post("/delete/:name", (req, res) -> {
            String name = req.params(":name");
            ChessGameDao.delete(name);
            res.redirect("/");
            return null;
        });
    }

    private void getGamePage() {
        get("/game/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame chessGame = ChessGameDao.load(req.params("name"));
            model.put("chess_game_name", req.params("name"));
            model.putAll(chessGame.getCurrentBoardByRawPosition());
            model.put("turn", chessGame.getTurn());
            model.put("result", chessGame.generateResult());
            return new ModelAndView(model, "chess_game.html");
        }, new HandlebarsTemplateEngine());
    }

    private void movePiece() {
        post("/move/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            ChessGame loadedChessGame = ChessGameDao.load(chessGameName);
            loadedChessGame.move(req.queryParams("source"), req.queryParams("target"));
            ChessGameDao.save(loadedChessGame);
            res.redirect("/game/" + chessGameName);
            return null;
        });
    }

    private void resetChessGame() {
        post("/reset/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            ChessGameDao.save(ChessGame.createInit(chessGameName));
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
