package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends Piece {
    private Bishop(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Bishop from(final Color color) {
        return new Bishop(PieceType.BISHOP, color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        if (source.isNotLinearFunction(target)) {
            return new ArrayList<>();
        }
        return source.calculateBetweenPoints(target);
    }
}
