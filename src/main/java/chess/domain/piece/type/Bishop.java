package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Waypoints;

public class Bishop extends Piece {

    public Bishop(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected void validateMovable(final Path path) {
        if (!path.isDiagonal()) {
            throw new IllegalArgumentException("비숍은 대각선으로만 이동할 수 있습니다.");
        }
    }

    @Override
    protected Waypoints waypointsPerType(final Path path) {
        return Waypoints.from(path.wayPoints());
    }
}
