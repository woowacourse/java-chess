package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.WayPoints;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class Rook extends Piece {

    public Rook(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected void validateMovable(final Path path) {
        if (!path.isStraight()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected WayPoints wayPointsWithCondition(final Path path) {
        return WayPoints.from(path.wayPoints());
    }
}
