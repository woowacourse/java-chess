package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Empty extends Piece {
    private static final char NAME = '.';

    public Empty(final char x, final char y) {
        super(false, x, y);
    }

    @Override
    public void move(final char x, final char y) {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    List<Position> extractMovablePositions() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public boolean isBlack() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public Position getPosition() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    char getName() {
        return NAME;
    }
}
