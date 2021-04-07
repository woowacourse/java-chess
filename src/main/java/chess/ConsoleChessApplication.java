package chess;

import chess.controller.ChessGameController;
import chess.domain.ChessGame;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        ChessGameController chessGameController = new ChessGameController();
        chessGameController.start(chessGame);
    }
}
