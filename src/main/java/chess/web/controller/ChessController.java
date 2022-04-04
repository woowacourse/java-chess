package chess.web.controller;

import chess.web.service.ChessService;
import spark.ModelAndView;

public class ChessController {
    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }


    public ModelAndView initGame() {
        return new ModelAndView(service.initBoard(), "board.html");
    }
}
