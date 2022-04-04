package web.controller;

import chess.Score;
import chess.piece.Color;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import web.dao.ChessGameDao;
import web.dto.GameStatus;

public class LobbyController {

    private final ChessGameDao chessGameDao;

    public LobbyController(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ModelAndView lobby(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("chess-games", chessGameDao.findAll());
        return new ModelAndView(model, "index.html");
    }

    public ModelAndView createChessGame(Request req, Response res) {
        String name = req.queryParams("name");
        Score initialScore = new Score(new BigDecimal("38.0"));
        chessGameDao.saveChessGame(name, GameStatus.READY, Color.WHITE, initialScore, initialScore);
        res.redirect("/");
        return null;
    }
}
