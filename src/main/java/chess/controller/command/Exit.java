package chess.controller.command;

import chess.domain.board.Board;

public final class Exit extends AbstractCommand {

    public Exit(final Board board) {
        super(board);
    }

    @Override
    public Command execute(final String command) {
        return this;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
