package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PieceType;

public abstract class Piece {

    private final Color color;
    private final PieceType pieceType;

    Piece(final Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    protected boolean isOppositeSide(final Piece piece) {
        return isNotSameSide(piece) && !piece.isEmpty();
    }

    protected boolean isNotSameSide(final Piece piece) {
        return this.color != piece.color;
    }

    public boolean isSideOf(final Color color) {
        return this.color == color;
    }

    protected boolean isEmpty() {
        return this.color == Color.EMPTY;
    }

    abstract public boolean isValidMove(Position from, Position to, Piece piece);

    public Color getSide() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public double getScore() {
        return pieceType.getScore();
    }
}
