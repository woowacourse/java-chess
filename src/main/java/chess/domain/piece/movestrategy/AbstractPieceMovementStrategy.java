package chess.domain.piece.movestrategy;

import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPieceMovementStrategy implements PieceMovementStrategy {

    @Override
    public List<PiecePosition> waypoints(final PiecePosition source,
                                         final PiecePosition destination,
                                         final Piece nullableEnemy) {
        validateMove(source, destination, nullableEnemy);
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
                             final Piece nullableEnemy) {
        validateSourceAndDestinationSame(source, destination);
        validateMoveWithNoAlly(source, destination, nullableEnemy);
    }

    protected void validateSourceAndDestinationSame(final PiecePosition source, final PiecePosition destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    protected abstract void validateMoveWithNoAlly(final PiecePosition source,
                                                   final PiecePosition destination,
                                                   final Piece nullableEnemy);

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
}
