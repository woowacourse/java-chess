package state;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

public final class End implements State {
    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void move(final Position source, final Position destination) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Position, Piece> getPieces() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State next() {
        throw new UnsupportedOperationException();
    }
}
