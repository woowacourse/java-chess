package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class Ready implements State {
    @Override
    public State start() {
        return new Playing(new Board(), Turn.from(Color.WHITE));
    }

    @Override
    public State end() {
        return new Finished();
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State status() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Board board() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
