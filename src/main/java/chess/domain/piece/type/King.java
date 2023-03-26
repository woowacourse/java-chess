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
            throw new IllegalArgumentException("킹은 한칸만 이동할 수 있습니다.");
        }
    }

    @Override
    protected WayPoints wayPointsWithCondition(final Path path) {
        return new WayPoints(Collections.emptyList());
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
