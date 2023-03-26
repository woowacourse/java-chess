package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public abstract class Piece {
    private final PieceType pieceType;
    protected final Color color;

    Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }
    public abstract List<Position> findMoveAblePositions(final Position source, final Position target, final Piece targetPiece);

    protected final void validateInvalidColor(final Piece targetPiece) {
        if (targetPiece.isSameColor(color)) {
            throw new IllegalArgumentException("같은 색깔의 기물을 선택할 수 없습니다.");
        }
    }

    public final boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public final boolean isNotSameColor(final Color color) {
        return this.color != color;
    }

    public final boolean isSamePieceType(final PieceType type) {
        return this.pieceType == type;
    }

    public final boolean isNotSamePieceType(final PieceType type) {
        return this.pieceType != type;
    }

    public final double getScore() {
        return pieceType.getScore();
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }
}
