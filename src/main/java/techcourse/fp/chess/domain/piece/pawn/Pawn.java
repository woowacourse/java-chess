package techcourse.fp.chess.domain.piece.pawn;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.PawnStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceType;

public abstract class Pawn extends Piece {

    private final PawnStrategy pawnStrategy;

    public Pawn(final Color color, final PieceType pieceType, final PawnStrategy pawnStrategy) {
        super(color, pieceType);
        this.pawnStrategy = pawnStrategy;
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Piece targetPiece) {
        if (isAttack(source, target, targetPiece) || isOneStepMove(source, target, targetPiece)) {
            return Collections.emptyList();
        }

        if (isTwoStepMove(source, target, targetPiece)) {
            return pawnStrategy.createPath(source);
        }

        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    private boolean isAttack(final Position source, final Position target, final Piece targetPiece) {
        return pawnStrategy.isAttackable(source,target) && isOpponent(targetPiece);
    }

    private boolean isOpponent(Piece otherPiece) {
        return color.isOpponent(otherPiece.getColor());
    }

    private boolean isOneStepMove(final Position source, final Position target, final Piece targetPiece) {
        return pawnStrategy.isOneStepDistance(source, target) && targetPiece.isEmpty();
    }

    private boolean isTwoStepMove(final Position source, final Position target, final Piece targetPiece) {
        return isStartPosition(source) && pawnStrategy.isTwoStepDistance(source, target) && targetPiece.isEmpty();
    }

    protected abstract boolean isStartPosition(final Position source);

    @Override
    public boolean isKing() {
        return false;
    }
}
