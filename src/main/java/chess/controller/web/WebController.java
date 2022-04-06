package chess.controller.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final BoardDao boardDao = new BoardDao();
    private ChessGame chessGame = ChessGame.createNotStart();

    public void run() {
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>(chessGame.getCurrentBoardByRawPosition());
            model.put("turn", chessGame.getTurn());
            model.put("result", chessGame.generateResult());
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());

        post("/move", (req, res) -> {
            chessGame.move(req.queryParams("source"), req.queryParams("target"));
            res.redirect("/");
            return null;
        });

        post("/save", (req, res) -> {
            chessGameDao.save(chessGame);
            boardDao.save(chessGame);
            res.redirect("/");
            return null;
        });

        get("/load", (req, res) -> {
            chessGame = chessGameDao.load(boardDao.load());
            res.redirect("/");
            return null;
        });

        post("/reset", (req, res) -> {
            chessGame = ChessGame.createInit();
            chessGameDao.save(chessGame);
            boardDao.save(chessGame);
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, req, res) -> {
            res.status(400);
            res.body("<a href=\"/\">HOME</a><br/>" + exception.getMessage());
        });
    }
}
