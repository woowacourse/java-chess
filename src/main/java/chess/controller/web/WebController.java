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
            res.redirect("/");
            return null;
        });

//        get("/game", (req, res) -> {
//            Map<String, Object> model = new HashMap<>(chessGame.getCurrentBoardByRawPosition());
//            model.put("turn", chessGame.getTurn());
//            model.put("result", chessGame.generateResult());
//            return new ModelAndView(model, "game.html");
//        }, new HandlebarsTemplateEngine());
//
//        post("/move", (req, res) -> {
//            chessGame.move(req.queryParams("source"), req.queryParams("target"));
//            res.redirect("/");
//            return null;
//        });
//
//        post("/save", (req, res) -> {
//            ChessGameDao.save(chessGame);
//            boardDao.save(chessGame);
//            res.redirect("/");
//            return null;
//        });
//
//        get("/load", (req, res) -> {
//            chessGame = ChessGameDao.load(boardDao.load());
//            res.redirect("/");
//            return null;
//        });
//
//        post("/reset", (req, res) -> {
//            chessGame = ChessGame.createInit();
//            ChessGameDao.save(chessGame);
//            boardDao.save(chessGame);
//            res.redirect("/");
//            return null;
//        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body("<a href=\"/\">HOME</a><br/>" + exception.getMessage());
        });
    }
}
