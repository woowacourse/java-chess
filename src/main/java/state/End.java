package state;

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
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State next() {
        throw new UnsupportedOperationException();
    }
}
