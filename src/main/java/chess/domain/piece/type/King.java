package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.WayPoints;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;

public class King extends Piece {

    public King(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected void validateMovable(final Path path) {
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected WayPoints wayPointsWithCondition(final Path path) {
        return WayPoints.from(Collections.emptyList());
    }
}
