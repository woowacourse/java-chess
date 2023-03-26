package chess.domain.state;

import chess.domain.Chess;
import chess.dto.Command;

public final class End extends State {
    public End(final Chess chess) {
        super(chess);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(final Command command) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State status() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }
}
