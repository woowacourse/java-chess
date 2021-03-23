package chess.domain.piece;

import chess.domain.result.Score;
import java.util.List;

public abstract class Piece {

    protected PieceType pieceType;
    protected PieceColor pieceColor;

    protected Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public abstract List<Direction> directions();

    public boolean isPawn() {
        return this.pieceType.is(PieceType.PAWN);
    }

    public boolean isKing() {
        return this.pieceType.is(PieceType.KING);
    }

    public boolean isKnight() {
        return this.pieceType.is(PieceType.KNIGHT);
    }

    public boolean isSameSide(Piece that) {
        return !isEnemyOrEmpty(that);
    }

    public boolean isEnemy(Piece that) {
        return this.pieceColor.equals(that.pieceColor.reversed());
    }

    public boolean isEmpty() {
        return this.pieceType.equals(PieceType.EMPTY);
    }

    public boolean isEnemyOrEmpty(Piece that) {
        return isEnemy(that) || that.isEmpty();
    }

    public boolean hasColor(PieceColor color) {
        return this.pieceColor.equals(color);
    }

    public String color() {
        return pieceColor.getColor();
    }

    public Score score() {
        return pieceType.getScore();
    }

    public String getName() {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return this.pieceType.toBlack();
        }
        return this.pieceType.getType();
    }
}
