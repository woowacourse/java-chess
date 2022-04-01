package chess;

import chess.domain.ChessGame;

public class ConsoleApplication {
    public static void main(String[] args) {
        final ChessGame chessGame = new ChessGame();
        chessGame.run();
    }
}
