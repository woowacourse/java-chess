package game.command;

import game.ChessGame;

public class EndCommand implements ChessCommand {

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.end();
    }
}
