package chess.domain.command;

import chess.domain.ChessGame;

public class StatusOnCommand implements Command {
    private static final String COMMAND_NAME = "status";

    public String run(ChessGame chessGame, String input) {
        return COMMAND_NAME;
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command);
    }
}
