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

    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;

    public void run() {
        staticFileLocation("/static");
        ChessGameDao chessGameDao = new ChessGameDao();
        getIndexPage(chessGameDao);
        createChessGame(chessGameDao);
        deleteChessGame(chessGameDao);
        getChessGamePage(chessGameDao);
        movePiece(chessGameDao);
        resetChessGame(chessGameDao);
        getException();
    }

    private void getIndexPage(final ChessGameDao chessGameDao) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chess_games", chessGameDao.loadAll());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());
    }

    private void createChessGame(final ChessGameDao chessGameDao) {
        post("/create_chess_game", (req, res) -> {
            String name = req.queryParams("name");
            chessGameDao.save(ChessGame.createBasic(name));
            res.redirect("/game/" + name);
            return null;
        });
    }

    private void deleteChessGame(final ChessGameDao chessGameDao) {
        post("/delete/:name", (req, res) -> {
            String name = req.params(":name");
            chessGameDao.delete(name);
            res.redirect("/");
            return null;
        });
    }

    private void getChessGamePage(final ChessGameDao chessGameDao) {
        get("/game/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame chessGame = chessGameDao.load(req.params("name"));
            model.put("chess_game_name", req.params("name"));
            model.putAll(chessGame.getCurrentBoardByRawPosition());
            model.put("turn", chessGame.getTurn());
            model.put("result", chessGame.generateResult());
            return new ModelAndView(model, "chess_game.html");
        }, new HandlebarsTemplateEngine());
    }

    private void movePiece(final ChessGameDao chessGameDao) {
        post("/move/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            ChessGame loadedChessGame = chessGameDao.load(chessGameName);
            String rawSource = req.queryParams("source").trim().toLowerCase();
            String rawTarget = req.queryParams("target").trim().toLowerCase();
            loadedChessGame.move(
                    rawSource.charAt(COLUMN_INDEX), Character.getNumericValue(rawSource.charAt(ROW_INDEX)),
                    rawTarget.charAt(COLUMN_INDEX), Character.getNumericValue(rawTarget.charAt(ROW_INDEX))
            );
            chessGameDao.save(loadedChessGame);
            res.redirect("/game/" + chessGameName);
            return null;
        });
    }

    private void resetChessGame(final ChessGameDao chessGameDao) {
        post("/reset/:chess_game_name", (req, res) -> {
            String chessGameName = req.params(":chess_game_name");
            chessGameDao.save(ChessGame.createBasic(chessGameName));
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
