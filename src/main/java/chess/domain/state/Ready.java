package chess.domain.state;

import chess.domain.Board;
import chess.domain.Position;

public class Ready extends State{
    public Ready(final Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State move(final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State start() {
        return new White(board);
    }

    @Override
    public State end() {
        return new End(board);
    }
}
