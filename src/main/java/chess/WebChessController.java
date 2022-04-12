package chess;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.service.WebChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;

public class WebChessController {
    private final WebChessService webChessService;
    private final Gson gson = new Gson();
    private ChessGame chessGame;

    public WebChessController() {
        this.webChessService = new WebChessService(new BoardDao(), new ChessGameDao());
        this.chessGame = new ChessGame();
    }

    public void run() {
        get("/", (req, res) -> new HandlebarsTemplateEngine().render(new ModelAndView(new HashMap<>(), "index.html")));
        get("/load", (req, res) -> {
            res.status(200);
            return gson.toJson(webChessService.loadGame(chessGame));
        });
        get("/move", (req, res) -> gson.toJson(webChessService.move(chessGame, req)));
        get("/end", (req, res) -> gson.toJson(webChessService.endGame(chessGame)));
    }
}
