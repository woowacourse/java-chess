package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.WayPoints;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;

public class Knight extends Piece {

    public Knight(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected void validateMovable(final Path path) {
        if (Math.abs(path.fileDistance()) == 1 && Math.abs(path.rankDistance()) == 2) {
            return;
        }
        if (Math.abs(path.fileDistance()) == 2 && Math.abs(path.rankDistance()) == 1) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    protected WayPoints wayPointsWithCondition(final Path path) {
        return WayPoints.from(Collections.emptyList());
    }
}
