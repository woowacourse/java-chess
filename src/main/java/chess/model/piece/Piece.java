package chess.model.piece;

import static chess.model.material.Color.NONE;

import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.Map;

public abstract class Piece implements MoveStrategy {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public static Piece of(Type type, Color color) {
        if (Type.PAWN.equals(type)) {
            return new Pawn(color);
        }
        if (Type.ROOK.equals(type)) {
            return new Rook(color);
        }
        if (Type.KNIGHT.equals(type)) {
            return new Knight(color);
        }
        if (Type.BISHOP.equals(type)) {
            return new Bishop(color);
        }
        if (Type.QUEEN.equals(type)) {
            return new Queen(color);
        }
        if (Type.KING.equals(type)) {
            return new King(color);
        }
        return new None(NONE);
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

    public boolean isExist() {
        return !isNone();
    }

    public boolean isNone() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isKing() {
        return false;
    }
}
