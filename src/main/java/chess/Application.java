package chess;

import chess.controller.ChessController;
import chess.repository.ChessGameRepository;

public class Application {

    public static void main(String[] args) {
        ChessGameRepository chessGameRepository = new ChessGameRepository();
        ChessController chessController = new ChessController(chessGameRepository);
        chessController.start();
    }
}
