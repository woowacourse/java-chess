package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;

public abstract class Piece implements Cloneable{

    private final Color color;
    private PiecePosition piecePosition;

    public Piece(final Color color, final PiecePosition piecePosition) {
        this.color = color;
        this.piecePosition = piecePosition;
    }

    public boolean existIn(final PiecePosition piecePosition) {
        return this.piecePosition.equals(piecePosition);
    }

    public Color color() {
        return color;
    }

    public PiecePosition piecePosition() {
        return piecePosition;
    }

    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
