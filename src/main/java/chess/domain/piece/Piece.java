package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract void validateMove(List<List<Piece>> board, Position source, Position target);

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isEmpty() {
        return false;
    }

    public String getNotation() {
        return pieceType.getNotation(color);
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public boolean isSamePieceType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }
}
