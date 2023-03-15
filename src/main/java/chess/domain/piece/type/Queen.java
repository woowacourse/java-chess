package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.WayPointsWithCondition;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class Queen extends Piece {

    public Queen(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected WayPointsWithCondition wayPointsWithCondition(final Path path) {
        if (path.isStraight() || path.isDiagonal()) {
            return WayPointsWithCondition.possible(path.wayPoints());
        }
        return WayPointsWithCondition.impossible();
    }
}
