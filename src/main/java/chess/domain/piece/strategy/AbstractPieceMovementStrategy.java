package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPieceMovementStrategy implements PieceMovementStrategy {

    protected final Color color;

    protected AbstractPieceMovementStrategy(final Color color) {
        this.color = color;
    }

    @Override
    public List<PiecePosition> waypoints(final PiecePosition source,
                                         final PiecePosition destination,
                                         final Piece nullableEnemy) throws IllegalArgumentException {
        validateMove(source, destination, nullableEnemy);
        return waypoint(source, destination);
    }

    private List<PiecePosition> waypoint(final PiecePosition source, final PiecePosition destination) {
        final List<PiecePosition> waypoints = new ArrayList<>();
        PiecePosition current = source;
        while (!current.equals(destination)) {
            current = current.move(current.direction(destination));
            waypoints.add(current);
        }
        waypoints.remove(destination);
        return waypoints;
    }

    @Override
    public void validateMove(final PiecePosition source,
                             final PiecePosition destination,
                             final Piece nullableEnemy) throws IllegalArgumentException {
        validateSourceAndDestinationSame(source, destination);
        validateAllyKill(nullableEnemy);
        validateMoveWithNoAlly(source, destination, nullableEnemy);
    }

    protected void validateSourceAndDestinationSame(final PiecePosition source, final PiecePosition destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    /**
     * @throws IllegalArgumentException 죽일 수 없는 경우
     */
    private void validateAllyKill(final Piece enemy) throws IllegalArgumentException {
        if (enemy == null) {
            return;
        }
        if (enemy.color() == color) {
            throw new IllegalArgumentException("아군이 있는 위치로는 이동할 수 없습니다.");
        }
    }

    protected abstract void validateMoveWithNoAlly(final PiecePosition source,
                                                   final PiecePosition destination,
                                                   final Piece nullableEnemy) throws IllegalArgumentException;

    protected boolean isStraight(final PiecePosition source, final PiecePosition destination) {
        return !(Math.abs(source.rankInterval(destination)) > 0
                && Math.abs(source.fileInterval(destination)) > 0);
    }

    protected boolean isDiagonal(final PiecePosition source, final PiecePosition destination) {
        return Math.abs(source.rankInterval(destination))
                == Math.abs(source.fileInterval(destination));
    }

    protected boolean isUnitDistance(final PiecePosition source, final PiecePosition destination) {
        return Math.abs(source.rankInterval(destination)) <= 1
                && Math.abs(source.fileInterval(destination)) <= 1;
    }

    protected boolean isHorizontal(final PiecePosition source, final PiecePosition destination) {
        return source.rankInterval(destination) == 0;
    }

    protected boolean isTwoVerticalMove(final PiecePosition source, final PiecePosition destination) {
        if (Math.abs(source.rankInterval(destination)) != 2) {
            return false;
        }
        return Math.abs(source.fileInterval(destination)) == 0;
    }

    @Override
    public Color color() {
        return this.color;
    }
}
