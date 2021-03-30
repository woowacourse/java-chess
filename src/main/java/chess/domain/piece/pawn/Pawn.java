package chess.domain.piece.pawn;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.MaxDistance;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Pawn extends Piece {

    private Pawn(final Owner owner, final Score score, final List<Direction> directions, final MaxDistance maxDistance) {
        super(owner, score, directions, maxDistance);
    }

    protected Pawn(final Owner owner, final List<Direction> directions) {
        this(owner, Score.PAWN_SCORE, directions, MaxDistance.PAWN);
    }

    public static Pawn getInstanceOf(final Owner owner) {
        if (owner.isSame(Owner.BLACK)) {
            return BlackPawn.getInstance();
        }
        if (owner.isSame(Owner.WHITE)) {
            return WhitePawn.getInstance();
        }

        throw new IllegalArgumentException("체스말은 색깔이 있어야 합니다.");
    }

    protected abstract boolean isFirstLine(final Horizontal horizontal);

    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        if (source.isStraight(target) && targetPiece.isEmptyPiece()) {
            return this.isValidStraightMove(source, target);
        }

        return this.isValidDiagonalMove(source, target, this.isEnemy(targetPiece));
    }

    private boolean isValidStraightMove(final Position source, final Position target) {
        if (this.isFirstLine(source.getHorizontal())) {
            return true;
        }

        return source.getDistance(target) == 1;
    }

    private boolean isValidDiagonalMove(final Position source, final Position target, final boolean isEnemy) {
        if (source.isDiagonal(target) && isEnemy) {
            return source.getDistance(target) == 1;
        }
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}