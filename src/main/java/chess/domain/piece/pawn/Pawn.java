package chess.domain.piece.pawn;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Pawn extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 2;

    private Pawn(final Owner owner, final Score score, final List<Direction> directions) {
        super(owner, score, directions);
    }

    protected Pawn(final Owner owner, final List<Direction> directions) {
        this(owner, new Score(1.0d), directions);
    }

    public static Pawn getInstanceOf(Owner owner) {
        if (owner.isSameTeam(Owner.BLACK)) {
            return BlackPawn.getInstance();
        }
        if (owner.isSameTeam(Owner.WHITE)) {
            return WhitePawn.getInstance();
        }

        throw new IllegalArgumentException("체스말은 색깔이 있어야 합니다.");
    }

    public abstract boolean isFirstLine(final Horizontal horizontal);

    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        if (source.isStraight(target)) {
            return isValidStraightMove(source, target);
        }

        return isValidDiagonalMove(source, target, isEnemy(targetPiece));
    }

    private boolean isValidStraightMove(final Position source, final Position target) {
        if (isFirstLine(source.getHorizontal())) {
            return true;
        }

        return source.getDistance(target) == 1;
    }

    private boolean isValidDiagonalMove(final Position source, final Position target, final boolean isEnemy) {
        if (isEnemy) {
            return source.getDistance(target) == 1;
        }
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}