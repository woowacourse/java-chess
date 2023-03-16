package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPointsWithCondition;

import java.util.List;

public class Pawn extends Piece {

    private boolean isMoved;

    public Pawn(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
        this.isMoved = false;
    }

    @Override
    protected void validateMovable(final Path path) {
        if (!matchDestinationByColor(path)) {
            throw new IllegalArgumentException();
        }
        if (!isMoved && isPawnSpecialDestination(path)) {
            return;
        }
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean matchDestinationByColor(final Path path) {
        if (color == Color.BLACK) {
            return path.isDestinationRelativelySouth();
        }
        return path.isDestinationRelativelyNorth();
    }

    private boolean isPawnSpecialDestination(final Path path) {
        if (Math.abs(path.rankDistance()) != 2) {
            return false;
        }
        return Math.abs(path.fileDistance()) == 0;
    }

    @Override
    protected WayPointsWithCondition wayPointsWithCondition(final Path path) {
        if (!isMoved && isPawnSpecialDestination(path)) {
            final List<PiecePosition> wayPoints = path.wayPoints();
            wayPoints.add(path.destination());
            return WayPointsWithCondition.possible(wayPoints);
        }
        return defaultMove(path);
    }

    private WayPointsWithCondition defaultMove(final Path path) {
        if (path.isDiagonal()) {
            return WayPointsWithCondition.onlyEnemy();
        }
        return WayPointsWithCondition.possible(List.of(path.destination()));
    }

    @Override
    public void move(final PiecePosition piecePosition) {
        this.piecePosition = piecePosition;
        this.isMoved = true;
    }
}
