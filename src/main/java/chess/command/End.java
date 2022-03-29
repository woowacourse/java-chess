package chess.command;

import chess.domain.board.Board;
import java.util.Optional;

public class End extends CommandChain {

    End() {
        super(Optional.empty());
    }

    @Override
    protected boolean canDoAction(Command command, Board board) {
        return command == Command.END;
    }

    @Override
    protected void doAction(String[] rawCommand, Board board) {
        board.terminateGame();
    }
}
