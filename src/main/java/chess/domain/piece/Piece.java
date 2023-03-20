package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public abstract class Piece  {
    private final PieceType pieceType;
    protected final Color color;

    Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public boolean isNotSameColor(final Color color) {
        return this.color != color;
    }

    public boolean isSamePieceType(final PieceType type) {
        return this.pieceType == type;
    }

    public boolean isNotSamePieceType(final PieceType type) {
        return this.pieceType != type;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public abstract List<Position> findPositions(final Position source, final Position target);
}
