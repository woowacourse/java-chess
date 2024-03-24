package chess.model.piece;

import static chess.model.material.Color.NONE;

import chess.model.Route;
import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.position.Position;
import java.util.Map;

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

    protected void validateRoute(Position source, Position target, Map<Position, Piece> pieces) {
        Route route = Route.of(source, target);
        if (route.isBlocked(pieces)) {
            throw new IllegalArgumentException("경로 상에 다른 기물이 존재합니다.");
        }
    }

    public boolean isEnemyTurn(int turnCount) {
        return color.isDifferentColor(turnCount);
    }

    public boolean isEnemy(Piece piece) {
        return piece.isDifferentColor(color) && piece.isDifferentColor(NONE);
    }

    public boolean isAlly(Piece piece) {
        return piece.isSameColor(color);
    }

    private boolean isDifferentColor(Color color) {
        return this.color != color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isNone() {
        return type.isNone();
    }

    public boolean isExist() {
        return type.isNotNone();
    }
}
