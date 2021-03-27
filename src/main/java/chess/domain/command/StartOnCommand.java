package chess.domain.command;

import chess.domain.ChessGame;

public class StartOnCommand implements Command {
    private static final String TEXT = "start";

    @Override
    public boolean isMatch(String commandText) {
        return TEXT.equals(commandText);
    }

    @Override
    public void execute(ChessGame chessGame, String[] splitCommand) {
        chessGame.start();
    }

    @Override
    public boolean isMustShowBoard() {
        return true;
    }

    @Override
    public boolean isMustShowStatus() {
        return false;
    }
}
