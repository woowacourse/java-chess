package chess;

import chess.controller.ChessController;
import chess.service.ChessGameService;

public class Application {

    public static void main(String[] args) {
        ChessGameService chessGameService = new ChessGameService();
        ChessController chessController = new ChessController(chessGameService);
        chessController.start();
    }
}
