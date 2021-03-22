package chess.domain.command;

import chess.domain.ChessGame;

public class EndOnCommand implements Command {
    private static final String COMMAND_NAME = "end";

    public String run(ChessGame chessGame, CommandInput commandInput) {
        return chessGame.end();
    }

    @Override
    public boolean isSameCommand(CommandInput commandInput) {
        return commandInput.isSameCommand(COMMAND_NAME);
    }
}
