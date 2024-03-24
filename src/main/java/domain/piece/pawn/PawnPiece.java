package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.Position;

public abstract class PawnPiece implements Piece {
    private static final int INITIAL_MOVE_DISTANCE = 2;
    private static final int NORMAL_MOVE_DISTANCE = 1;

    private final Color color;

    protected PawnPiece(Color color) {
        this.color = color;
    }

    public final void validateMovement(final Position source, final Position target, final Piece other) {
        validateColorDifference(other);
        validateForwardMovement(source, target);
        validateFawnMovement(source, target, other);
    }

    private void validateColorDifference(final Piece other) {
        if (this.color() == other.color()) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    protected abstract void validateForwardMovement(final Position source, final Position target);

    private void validateFawnMovement(final Position source, final Position target, final Piece other) {
        if (!isPawnMovement(source, target, other)) {
            throw new IllegalArgumentException("잘못된 방향으로 이동하고 있습니다.");
        }
    }

    private boolean isPawnMovement(final Position source, final Position target, final Piece other) {
        return isMovingTwoDistanceForward(source, target, other) ||
                isMovingOneDistanceForward(source, target, other) ||
                isMovingOneDistanceDiagonal(source, target, other);
    }

    private boolean isMovingTwoDistanceForward(final Position source, final Position target, final Piece other) {
        return isAtSameRank(source) && source.isStraightAt(target)
                && source.isDistanceAt(target, INITIAL_MOVE_DISTANCE) && nonPieceExist(other);
    }

    protected abstract boolean isAtSameRank(final Position source);

    private boolean isMovingOneDistanceForward(final Position source, final Position target, final Piece other) {
        return source.isStraightAt(target) && source.isDistanceAt(target, NORMAL_MOVE_DISTANCE) && nonPieceExist(other);
    }

    private boolean isMovingOneDistanceDiagonal(final Position source, final Position target, final Piece other) {
        return source.isDiagonalAt(target) && source.isAdjacentAt(target) && isOpposite(other);
    }

    private boolean nonPieceExist(Piece other) {
        return other.color().isNeutrality();
    }

    private boolean isOpposite(Piece other) {
        if (nonPieceExist(other)) {
            return false;
        }
        return this.color() != other.color();
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public final Type type() {
        return Type.PAWN;
    }
}
