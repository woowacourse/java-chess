package chess.domain.command;

import chess.domain.ChessGame;

public class EndOnCommand implements Command {
    private static final String COMMAND_NAME = "end";

    public String run(ChessGame chessGame, String input) {
        return chessGame.end();
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command);
    }
}
