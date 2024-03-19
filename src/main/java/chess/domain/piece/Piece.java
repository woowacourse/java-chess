package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public abstract class Piece {
    private final Color color;
    private final PieceType pieceType;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isEmpty() {
        return getPieceType() == PieceType.NONE;
    }

    public boolean isSameColor(Piece piece) {
        return color.isSame(piece.color);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }
}
