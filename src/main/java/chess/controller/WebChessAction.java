package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.game.ChessResult;
import chess.service.ChessGameService;
import spark.Request;
import spark.Response;

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

    public String start(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        BoardDto board = chessGameService.start(req.queryParams("boardName"));
        model.put("board", board);
        return render(model, "game.html");
    }

    public String move(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        BoardDto board = chessGameService.move(
                req.queryParams("boardName"),
                req.queryParams("source"),
                req.queryParams("target")
        );
        model.put("board", board);
        return render(model, "game.html");
    }

    public String end(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public String continueGame(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        BoardDto board = chessGameService.continueGame(req.queryParams("boardName"));
        model.put("board", board);
        return render(model, "game.html");
    }

    public String status(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        ChessResult chessResult = chessGameService.result(req.queryParams("boardName"));
        BoardDto board = chessGameService.resultBoard(req.queryParams("boardName"));
        model.put("board", board);
        model.put("result", chessResult);
        return render(model, "game.html");
    }
}
