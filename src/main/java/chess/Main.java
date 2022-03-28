package chess;

import chess.controller.ChessController;
import chess.service.ChessService;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new ChessService());
        chessController.run();
    }
}
