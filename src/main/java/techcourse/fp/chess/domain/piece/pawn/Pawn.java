package techcourse.fp.chess.domain.piece.pawn;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Directions;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceType;

public abstract class Pawn extends Piece {

    protected final Directions attackDirections;
    protected final Direction moveDirection;

    public Pawn(final Color color, final PieceType pieceType, final Directions attackDirections,
                final Direction moveDirection) {
        super(color, pieceType);
        this.attackDirections = attackDirections;
        this.moveDirection = moveDirection;
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Piece targetPiece) {
        if (isAttack(source, target, targetPiece) || isOneStepMove(source, target, targetPiece)) {
            return Collections.emptyList();
        }

        if (isTwoStepMove(source, target, targetPiece)) {
            return createTwoStepPath(source);
        }

        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    private boolean isAttack(final Position source, final Position target, final Piece targetPiece) {
        return attackDirections.hasMovableDirection(source,target) && isOpponent(targetPiece);
    }

    private boolean isOpponent(Piece otherPiece) {
        return color.isOpponent(otherPiece.getColor());
    }

    private boolean isOneStepMove(final Position source, final Position target, final Piece targetPiece) {
        final int file = target.getGapOfFileOrder(source);
        final int rank = target.getGapOfRankOrder(source);
        return moveDirection.isSame(file, rank) && targetPiece.isEmpty();
    }

    private boolean isTwoStepMove(final Position source, final Position target, final Piece targetPiece) {
        return isStartPosition(source) && isTwoStepDistance(source, target) && targetPiece.isEmpty();
    }

    public boolean isTwoStepDistance(Position source, Position target) {
        return target.getRankOrder() == source.getRankOrder() + moveDirection.getRank() * 2;
    }
    protected abstract boolean isStartPosition(final Position source);

    private List<Position> createTwoStepPath(final Position source) {
        return List.of(Position.of(source.getFile(), Rank.of(source.getRankOrder() + moveDirection.getRank())));
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
