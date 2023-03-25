package chess.domain.state;

import chess.cache.PieceCache;
import chess.domain.Board;
import chess.domain.Color;

public abstract class Running extends State {
    protected Running(final Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State start() {
        return new White(Board.from(PieceCache.create()));
    }

    @Override
    public State end() {
        return new End(board);
    }

    @Override
    public double calculateScore(Color color) {
        return board.calculateScore(color);
    }
}
