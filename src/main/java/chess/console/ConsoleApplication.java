package chess.console;

import chess.console.game.ChessGame;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        chessGame.run();
    }
}
