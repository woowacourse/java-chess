package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPoints;

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
        throw new IllegalArgumentException("나이트는 그렇게 움직일 수 없습니다.");
    }

    @Override
    protected WayPoints wayPointsWithCondition(final Path path) {
        return new WayPoints(Collections.emptyList());
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
