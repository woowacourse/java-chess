package chess;

import chess.controller.ChessGame;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        try {
            chessGame.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
