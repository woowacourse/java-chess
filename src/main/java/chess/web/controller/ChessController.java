package chess.web.controller;

import chess.web.service.ChessService;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessController {
    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    public void initGame() {
        service.initBoard();
    }

    public ModelAndView getBoard() {
        return new ModelAndView(service.getBoard(), "board.html");
    }

    public void move(Request req, Response res) {
        String body = req.body();
        String[] keyValues = body.split("&");
        String from = keyValues[0].split("=")[1];
        String to = keyValues[1].split("=")[1];
        service.move(from, to);
    }

    public ModelAndView status() {
        return new ModelAndView(service.getScores(), "status.html");
    }
}
