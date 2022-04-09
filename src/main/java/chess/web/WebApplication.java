package chess.web;

import chess.web.controller.ChessController;
import chess.web.service.ChessService;

public class WebApplication {
    public static void main(String[] args) {
        ChessService chessService = new ChessService();
        ChessController chessController = new ChessController(chessService);

        chessController.run();
    }
}
