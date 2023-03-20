package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public final class Rook extends Piece {
    private Rook(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Rook from(final Color color) {
        return new Rook(PieceType.ROOK, color);
    }

    @Override
    public List<Position> findMoveAblePositions(final Position source, final Position target, final Piece targetPiece) {
        if (targetPiece.isSameColor(color)) {
            throw new IllegalArgumentException("같은 색깔의 기물을 선택할 수 없습니다.");
        }
        if (source.isNotConstantFunction(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
        return source.calculateBetweenPoints(target);
    }
}
