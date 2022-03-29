package chess.command;

import chess.domain.board.Board;
import java.util.Optional;

public class End extends CommandChain {

    End() {
        super(Optional.empty());
    }

    @Override
    protected boolean canDoAction(final Command command, final Board board) {
        return command == Command.END;
    }

    @Override
    protected void doAction(final ParsedCommand parsedCommand, final Board board) {
        board.terminateGame();
    }
}
