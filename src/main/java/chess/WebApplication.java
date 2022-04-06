package chess;

import chess.controller.ChessWebController;
import chess.service.ChessService;

public class WebApplication {

    public static void main(String[] args) {
        new ChessWebController(new ChessService()).run();
    }
}
