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

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chess_games", ChessGameDao.loadAll());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());

        post("/create_game", (req, res) -> {
            String name = req.queryParams("name");
            ChessGameDao.save(ChessGame.createInit(name));
            res.redirect("/game/" + name);
            return null;
        });

        post("/delete/:name", (req, res) -> {
            String name = req.params(":name");
            ChessGameDao.delete(name);
            res.redirect("/");
            return null;
        });

        get("/game/:name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame chessGame = ChessGameDao.load(req.params("name"));
            model.put("game_name", req.params("name"));
            model.putAll(chessGame.getCurrentBoardByRawPosition());
            model.put("turn", chessGame.getTurn());
            model.put("result", chessGame.generateResult());
            return new ModelAndView(model, "game.html");
        }, new HandlebarsTemplateEngine());

        post("/move/:game_name", (req, res) -> {
            String gameName = req.params(":game_name");
            ChessGame loadedChessGame = ChessGameDao.load(gameName);
            loadedChessGame.move(req.queryParams("source"), req.queryParams("target"));
            ChessGameDao.save(loadedChessGame);
            res.redirect("/game/" + gameName);
            return null;
        });

        post("/reset/:game_name", (req, res) -> {
            String gameName = req.params(":game_name");
            ChessGameDao.save(ChessGame.createInit(gameName));
            res.redirect("/game/" + gameName);
            return null;
        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body(exception.getMessage());
        });
    }
}
