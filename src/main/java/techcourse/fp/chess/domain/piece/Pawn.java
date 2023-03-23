package techcourse.fp.chess.domain.piece;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;

public abstract class Pawn extends Piece {

    private final MovingStrategy movingStrategy;

    public Pawn(final Color color, final MovingStrategy movingStrategy) {
        super(color);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Piece targetPiece) {
        if (isAttack(source, target, targetPiece) || isOneStepMove(source, target, targetPiece)) {
            return Collections.emptyList();
        }

        if (isTwoStepMove(source, target, targetPiece)) {
            return movingStrategy.createPath(source, target);
        }

        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    private boolean isAttack(final Position source, final Position target, final Piece targetPiece) {
        return source.isOnDiagonal(target) && isOpponent(targetPiece);
    }

    private boolean isOpponent(Piece otherPiece) {
        return color.isOpponent(otherPiece.color);
    }

    private boolean isOneStepMove(final Position source, final Position target, final Piece targetPiece) {
        return source.isUpDown(target) && targetPiece.isEmpty();
    }

    private boolean isTwoStepMove(final Position source, final Position target, final Piece targetPiece) {
        return isStartPosition(source) && isTwoDistance(source, target, targetPiece);
    }

    private boolean isTwoDistance(final Position source, final Position target, final Piece targetPiece) {
        return isStartPosition(source) && source.isUpDownTwo(target) && targetPiece.isEmpty();
    }

    protected abstract boolean isStartPosition(final Position source);
}
