package chess.domain.piece;

import chess.domain.position.Movement;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    final void validateSameColor(final Piece other) {
        if (color.isSameColor(other.color)) {
            throw new IllegalStateException("같은 색 말의 위치로 이동할 수 없습니다.");
        }
    }

    final public boolean isBlack() {
        return color == Color.BLACK;
    }

    final public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    final protected Path generatePathFromTo(final Position from, final Position to, final Movement movement) {
        Position next = from;
        final List<Position> positions = new ArrayList<>();
        while (true) {
            next = next.moveBy(movement);
            if (next.equals(to)) {
                break;
            }
            positions.add(next);
        }
        return new Path(positions);
    }

    public abstract Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination);
}
