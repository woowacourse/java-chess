package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Queen extends Piece {
    private Queen(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Queen from(final Color color) {
        return new Queen(PieceType.QUEEN, color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        if (source.isNotLinearFunction(target) && source.isNotConstantFunction(target)) {
            return new ArrayList<>();
        }
        return source.calculateBetweenPoints(target);
    }
}
