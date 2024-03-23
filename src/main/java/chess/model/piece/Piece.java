package chess.model.piece;

import chess.model.Position;
import chess.model.material.Color;
import chess.model.material.Type;

public abstract class Piece implements MoveStrategy {

    protected final Type type;
    protected final Color color;

    protected Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public static Piece of(Type type, Color color) {
        if (Type.PAWN.equals(type)) {
            return new Pawn(type, color);
        }
        if (Type.ROOK.equals(type)) {
            return new Rook(type, color);
        }
        if (Type.KNIGHT.equals(type)) {
            return new Knight(type, color);
        }
        if (Type.BISHOP.equals(type)) {
            return new Bishop(type, color);
        }
        if (Type.QUEEN.equals(type)) {
            return new Queen(type, color);
        }
        if (Type.KING.equals(type)) {
            return new King(type, color);
        }
        return new None(type, color);
    }

    protected final int calculateRowDifference(Position source, Position target) {
        return target.getRow() - source.getRow();
    }

    protected final int calculateColumnDifference(Position source, Position target) {
        return target.getColumn() - source.getColumn();
    }

    public boolean isEnemy(int turnCount) {
        return color.isDifferentColor(turnCount);
    }

    public boolean isAlly(int turnCount) {
        return color.isSameColor(turnCount);
    }

    public boolean isExist() {
        return type.isNotNone();
    }

    public boolean isNone() {
        return type.isNone();
    }

    public boolean isPawn() {
        return type.isPawn();
    }

    public boolean isKnight() {
        return type.isKnight();
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }


    public boolean isSameColor(Color color) {
        return this.color == color;
    }
}
