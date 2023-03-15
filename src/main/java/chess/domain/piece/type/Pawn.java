package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.WayPointsWithCondition;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;

public class Pawn extends Piece {

    private boolean isMoved;

    public Pawn(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
        this.isMoved = false;
    }

    @Override
    protected WayPointsWithCondition wayPointsWithCondition(final Path path) {
        if (!matchDestinationByColor(path)) {
            return WayPointsWithCondition.impossible();
        }
        if (!isMoved && isPawnSpecialDestination(path)) {
            return WayPointsWithCondition.possible(path.wayPoints());
        }
        return defaultMove(path);
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

    private WayPointsWithCondition defaultMove(final Path path) {
        if (!path.isUnitDistance()) {
            return WayPointsWithCondition.impossible();
        }
        if (path.isDiagonal()) {
            return WayPointsWithCondition.onlyEnemy();
        }
        return WayPointsWithCondition.possible(Collections.emptyList());
    }

    public void move(final PiecePosition piecePosition) {
        this.piecePosition = piecePosition;
        this.isMoved = true;
    }
}
