package chess.domain.command;

import chess.domain.game.ChessGame;

public class Start implements Command {

    private static final String START_COMMAND = "start";
    private final ChessGame chessGame;

    public Start(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execution(String text) {
        chessGame.start();
    }

    @Override
    public boolean isMatchedCommand(String text) {
        return START_COMMAND.equalsIgnoreCase(text);
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
