package chess.domain.command;

import chess.domain.ChessGame;

public class StatusOnCommand implements Command {
    private static final String COMMAND_NAME = "status";

    public String run(ChessGame chessGame, CommandInput commandInput) {
        return COMMAND_NAME;
    }

    @Override
    public boolean isSameCommand(CommandInput commandInput) {
        return commandInput.isSameCommand(COMMAND_NAME);
    }
}
