package domain.piece;

import domain.position.Position;

public abstract class AbstractColorValidatablePiece extends AbstractPiece {
    protected AbstractColorValidatablePiece(final Color color) {
        super(color);
    }

    @Override
    public void validateMovement(final Position resource, final Position target, final Piece other) {
        validateColorDifference(other);
        validatePieceMovement(resource, target);
    }

    private void validateColorDifference(final Piece other) {
        if (this.getColor() == other.getColor()) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    protected abstract void validatePieceMovement(final Position resource, final Position target);
}
