package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public class Empty extends Piece {
    private static final char NAME = '.';

    public Empty(final char x, final char y) {
        super(false, x, y);
    }

    public Empty(Position position) {
        this(position.getX(), position.getY());
    }

    @Override
    public boolean isBlack() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public List<Direction> getDirections() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public int getStepRange() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public boolean isSameColor(Piece other) {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public char getName() {
        return NAME;
    }

    @Override
    public double getScore() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }
}
