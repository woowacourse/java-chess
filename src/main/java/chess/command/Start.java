package chess.command;

import chess.controller.ChessGame;

public class Start implements Runnable {
    @Override
    public void run() {
        ChessGame.run();
    }
}
