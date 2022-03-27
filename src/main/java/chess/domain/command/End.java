package chess.domain.command;

import chess.domain.board.Board;

public final class End extends CommandState {
    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public CommandState execute(String command) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatusResult getStatus() {
        throw new UnsupportedOperationException();
    }
}
