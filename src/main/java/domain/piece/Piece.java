package domain.piece;

import domain.board.Position;

import java.util.Map;

public abstract class Piece {
    protected final PieceColor color;

    protected Piece(final PieceColor color) {
        this.color = color;
    }

    public abstract void checkMovable(final Position source, final Position destination, final Map<Position, Piece> piecePositions);

    protected boolean checkEnemy(final Piece otherPiece) {
        return otherPiece.isEnemy(this.color);
    }

    public boolean isEnemy(final PieceColor otherColor) {
        return this.color != otherColor;
    }
}
