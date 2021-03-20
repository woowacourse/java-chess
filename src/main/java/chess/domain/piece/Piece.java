package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

import java.util.Collections;
import java.util.List;

public abstract class Piece {
    private final Owner owner;
    private final List<Direction> directions;
    private final Score score;

    public Piece(final Owner owner, final Score score, final List<Direction> directions) {
        this.owner = owner;
        this.score = score;
        this.directions = directions;
    }

    public abstract boolean isReachable(final Position source, final Position target, final Piece targetPiece);


    // TODO :: View에서 해야할 일
    public final String decideUpperOrLower(final String symbol) {
        if (owner.equals(Owner.BLACK)) {
            return symbol.toUpperCase();
        }

        if (owner.equals(Owner.WHITE)) {
            return symbol.toLowerCase();
        }

        return symbol;
    }

    public abstract String getSymbol();

    public final boolean isEnemy(final Piece other) {
        return this.owner.isEnemy(other.owner);
    }

    public final boolean isSameTeam(final Piece other) {
        return this.owner.isSameTeam(other.owner);
    }

    public final List<Direction> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public boolean isOwner(final Owner owner) {
        return this.owner.isSameTeam(owner);
    }

    public abstract int getMaxDistance();

    // XXX :: instance of 사용? 오버라이드?
    public boolean isKing() {
        return this instanceof King;
    }

    public final Score score() {
        return this.score;
    }
}
