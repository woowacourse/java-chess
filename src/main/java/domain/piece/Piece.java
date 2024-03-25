package domain.piece;

import domain.board.Position;

import java.util.Map;

public abstract class Piece {
    protected final PieceColor color;

    public Piece(final PieceColor color) {
        this.color = color;
    }

    public abstract void move(final Position source, final Position destination, final Map<Position, Piece> piecePositions);

    public abstract PieceType pieceType();

    protected boolean checkEnemy(final Piece otherPiece) {
        return otherPiece.isEnemy(this.color);
    }

    public boolean isEnemy(final PieceColor otherColor) {
        return this.color != otherColor;
    }

    public PieceColor pieceColor() {
        return color;
    }
}
