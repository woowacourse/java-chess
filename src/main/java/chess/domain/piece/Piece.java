package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.result.Score;
import java.util.List;

public abstract class Piece {

    protected PieceType pieceType;
    protected PieceColor pieceColor;
    protected MoveStrategy moveStrategy;

    protected Piece(PieceType pieceType, PieceColor pieceColor, MoveStrategy moveStrategy) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.moveStrategy = moveStrategy;
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

    public String getName() {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return this.pieceType.toBlack();
        }
        return this.pieceType.getType();
    }

    public String color() {
        return pieceColor.getColor();
    }

    public Score score() {
        return pieceType.getScore();
    }

    public boolean isSameSide(Piece that) {
        return !isEnemyOrEmpty(that);
    }
}
