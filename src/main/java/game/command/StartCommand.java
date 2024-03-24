package game.command;

import game.ChessGame;

public class StartCommand implements ChessCommand {

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.start();
    }
}
