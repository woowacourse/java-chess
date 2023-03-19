package chess;

import chess.game.ChessGameLauncher;

public class Application {
    public static void main(String[] args) {
        var gameLauncher = new ChessGameLauncher();
        gameLauncher.execute();
    }
}
