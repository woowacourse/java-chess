package chess;

import chess.controller.ChessGame;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        chessGame.run();
    }
}
