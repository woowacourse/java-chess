package chess.domain.piece;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

import java.util.List;

public abstract class Pawn extends Piece {
    private static final Distance DIAGONAL_DISTANCE_WHEN_EXIST_ENEMY = Distance.ONE;
    private static final Distance STRAIGHT_DISTANCE_WHEN_ON_FIRST_LINE = Distance.TWO;
    private static final Distance STRAIGHT_DISTANCE = Distance.ONE;

    private static final Pawn BLACK_PAWN = new Pawn(Owner.BLACK, Direction.blackPawnDirections()) {
        @Override
        public boolean isFirstLine(final Horizontal horizontal) {
            return horizontal.equals(Horizontal.SEVEN);
        }
    };

    private static final Pawn WHITE_PAWN = new Pawn(Owner.WHITE, Direction.whitePawnDirections()) {
        @Override
        public boolean isFirstLine(final Horizontal horizontal) {
            return horizontal.equals(Horizontal.TWO);
        }
    };

    // XXX :: Symbol view로 빼기
    private Pawn(final Owner owner, final List<Direction> directions) {
        super(
                owner,
                Score.PAWN,
                directions,
                Distance.TWO,
                "P"
        );
    }

    public static Pawn getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_PAWN;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_PAWN;
        }

        throw new IllegalArgumentException("Invalid pawn");
    }

    public abstract boolean isFirstLine(final Horizontal horizontal);

    @Override
    public boolean isReachable(final Direction direction, final Distance distance, final Position position, final Piece targetPiece) {
        if (!super.isReachable(direction, distance, position, targetPiece)) {
            return false;
        }

        if (distance.equals(STRAIGHT_DISTANCE_WHEN_ON_FIRST_LINE) && direction.isStraight()) {
            return isFirstLine(position.getHorizontal());
        }

        if (direction.isDiagonal() && distance.equals(DIAGONAL_DISTANCE_WHEN_EXIST_ENEMY)) {
            return isEnemy(targetPiece);
        }

        return direction.isStraight() && distance.equals(STRAIGHT_DISTANCE);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}