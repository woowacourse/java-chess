package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

abstract public class Piece {

    protected Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public boolean isEnemy(final Piece piece) {
        return piece.team != team;
    }

    public boolean isAlly(final Team team) {
        return this.team == team;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public abstract Direction findDirection(final Square current, final Square destination);

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + team +
                '}';
    }

    public Team getColor() {
        return team;
    }
}
