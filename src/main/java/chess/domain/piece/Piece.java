package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    abstract public void validateMove(Board board, Position sourcePosition, Position targetPosition);

    public boolean isBlack() {
        return color.isBlack();
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    public final boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isEmpty() {
        return false;
    }

    public final String getNotation() {
        return pieceType.getNotation(color);
    }

    public final double getScore() {
        return pieceType.getScore();
    }

    public final boolean isSamePieceType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
