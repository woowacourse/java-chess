package chess.web.controller;

import chess.service.BoardDto;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessController {
    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    public void initGame() {
        service.initGame();
    }

    public ModelAndView getBoard() {
        Map<String, Object> model = new HashMap<>();
        if (service.isWaitingOrRunning()) {
            model.put("board", service.getBoard());
            return new ModelAndView(model, "board.html");
        }
        return null;
    }

    public void move(Request req, Response res) {
        String body = req.body();
        String[] keyValues = body.split("&");
        String from = keyValues[0].split("=")[1];
        String to = keyValues[1].split("=")[1];
        service.move(from, to);
    }

    public ModelAndView status() {
        return new ModelAndView(service.getResult(), "result.html");
    }
}
