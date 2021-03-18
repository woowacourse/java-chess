package chess;

import chess.controller.ChessGame;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGame controller = new ChessGame();
        controller.run();
    }
}
