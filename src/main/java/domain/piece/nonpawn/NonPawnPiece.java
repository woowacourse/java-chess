package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;

public abstract class NonPawnPiece implements Piece {
    private final Color color;

    protected NonPawnPiece(Color color) {
        this.color = color;
    }

    @Override
    public void validate(final Position resource, final Position target, final Piece other) {
        validateColorDifference(other);
        validateMovement(resource, target);
    }

    private void validateColorDifference(final Piece other) {
        if (this.getColor().equals(other.getColor())) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    protected abstract void validateMovement(final Position resource, final Position target);

    @Override
    public Color getColor() {
        return color;
    }
}
