package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {
    protected final Color color;
    protected final PieceType pieceType;
    protected int moveCount;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
        this.moveCount = 0;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isNeverDisplaced() {
        return moveCount == 0;
    }

    public Piece displaced() {
        this.moveCount++;
        return this;
    }

    public boolean isBlank() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isEnPassantAvailable() {
        return false;
    }

    protected abstract String baseSignature();

    public abstract boolean isMovable(Map<Position, Piece> board, Position source, Position target);

    public abstract List<Position> findRoute(Position source, Position target);

    public abstract double score();

    public String getPieceName() {
        return (pieceType.name() + color.name()).toLowerCase();
    }

    public int getMoveCount() {
        return moveCount;
    }
}
