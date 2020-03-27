package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public class Finished implements State {
    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Board board() {
        return new Board();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
