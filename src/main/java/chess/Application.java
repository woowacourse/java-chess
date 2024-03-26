package chess;

import chess.controller.ChessGame;

public final class Application {

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        chessGame.run();
    }
}
