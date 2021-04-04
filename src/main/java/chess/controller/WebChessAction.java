package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.TeamColor;
import chess.domain.game.ChessResult;
import chess.service.ChessGameService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class WebChessAction {

    private ChessGameService chessGameService;

    public WebChessAction(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public String index(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public String start(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        BoardDto board = chessGameService.start();
        model.put("board", board);
        return render(model, "game.html");
    }

    public String move(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        BoardDto board = chessGameService.move(req.queryParams("source"), req.queryParams("target"));

        model.put("board", board);
        model.put("checked", board.isChecked());
        model.put("isKingDead", board.isKingDead());
        return render(model, "game.html");
    }

    public String end(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public String continueGame(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        BoardDto board = chessGameService.continueGame();

        model.put("board", board);
        model.put("checked", board.isChecked());
        model.put("isKingDead", board.isKingDead());
        return render(model, "game.html");
    }

    public String status(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        ChessResult chessResult = chessGameService.result();
        String winner = chessGameService.winner(chessResult.getWhiteTeamScore(), chessResult.getBlackTeamScore());
        BoardDto board = chessGameService.status();
        model.put("board", board);
        model.put("result", chessResult);
        model.put("winner", winner);
        return render(model, "game.html");
    }
}
