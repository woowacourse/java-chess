package chess.domain.command;

import chess.domain.ChessGame;

public class EndOnCommand implements Command {
    private static final String TEXT = "end";

    @Override
    public boolean isMatch(String commandText) {
        return TEXT.equals(commandText);
    }

    @Override
    public void execute(ChessGame chessGame, String[] splitCommand) {
        chessGame.end();
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
