package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public final class Queen extends Piece {
    private Queen(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Queen from(final Color color) {
        return new Queen(PieceType.QUEEN, color);
    }

    @Override
    public List<Position> findMoveAblePositions(final Position source, final Position target) {
        if (source.isNotLinearFunction(target) && source.isNotConstantFunction(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
        return source.calculateBetweenPoints(target);
    }
}
