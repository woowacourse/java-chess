package chess.domain.command;

import chess.domain.ChessGame;

public class EndOnCommand extends MainCommand {
    private static final String COMMAND_NAME = "end";

    public EndOnCommand(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public String run(String input) {
        getChessGame().end();
        return null;
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command);
    }
}
