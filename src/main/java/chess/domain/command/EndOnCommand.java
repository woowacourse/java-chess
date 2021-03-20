package chess.domain.command;

import chess.domain.ChessGame;

public class EndOnCommand implements Command {
    private static final String COMMAND_NAME = "end";

    @Override
    public String run(String input, ChessGame chessGame) {
        chessGame.end();
        return null;
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command);
    }
}
