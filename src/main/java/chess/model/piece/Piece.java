package chess.model.piece;

import static chess.model.material.Color.NONE;

import chess.model.material.Color;
import chess.model.position.Position;
import chess.model.position.Route;

public abstract class Piece {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract Route findRoute(Position source, Position target);

    public boolean canAttack(Position source, Position target) {
        return false;
    }

    public boolean isEnemyWith(Piece piece) {
        return piece.isDifferentColor(color) && piece.isDifferentColor(NONE);
    }

    public boolean isAllyWith(Piece piece) {
        return piece.isSameColor(color);
    }

    public boolean isDifferentColor(Color color) {
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
