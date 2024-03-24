package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;

public abstract class NonPawnPiece implements Piece {
    private final Color color;

    protected NonPawnPiece(final Color color) {
        this.color = color;
    }

    @Override
    public final void validateMovement(final Position source, final Position target, final Piece other) {
        validateColorDifference(other);
        validateDirection(source, target);
    }

    private void validateColorDifference(final Piece other) {
        if (this.color() == other.color()) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    protected abstract void validateDirection(final Position source, final Position target);

    @Override
    public Color color() {
        return color;
    }
}
