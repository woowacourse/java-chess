package chess.domain.state;

import chess.domain.Chess;
import chess.domain.Color;
import chess.dto.Command;

public final class Start extends State {
    public Start(final Chess chess) {
        super(chess);
    }

    @Override
    public State start() {
        return new White(chess);
    }

    @Override
    public State move(final Command command) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        return new End(chess);
    }

    @Override
    public State status() {
        return new Status(chess, Color.WHITE);
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
